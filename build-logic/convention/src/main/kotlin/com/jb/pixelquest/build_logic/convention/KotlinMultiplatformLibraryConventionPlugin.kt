package com.jb.pixelquest.build_logic.convention

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.android.library")
            }

            // Android 설정
            extensions.configure<LibraryExtension> {
                compileSdk = libs.versionInt("compileSdk")
                
                defaultConfig {
                    minSdk = libs.versionInt("minSdk")
                }
                
                compileOptions {
                    sourceCompatibility = org.gradle.api.JavaVersion.VERSION_11
                    targetCompatibility = org.gradle.api.JavaVersion.VERSION_11
                }
                
                // KMP 모듈에서는 Java 소스가 없으므로 Java 컴파일 비활성화
                sourceSets {
                    getByName("main") {
                        java.srcDirs("src/main/kotlin")
                    }
                }
            }
            
            // Java 컴파일 작업 비활성화 (KMP에서는 Kotlin만 사용)
            tasks.withType<JavaCompile> {
                enabled = false
            }

            // KMP 설정
            extensions.configure<KotlinMultiplatformExtension> {
                androidTarget {
                    compilations.all {
                        kotlinOptions {
                            jvmTarget = "11"
                        }
                    }
                }
                
                // jvmToolchain은 Java 11이 설치되어 있을 때만 필요
                // 설치되어 있지 않으면 kotlinOptions.jvmTarget만으로도 충분
                // jvmToolchain(11)
            }
            
            // sourceSets dependencies는 afterEvaluate에서 설정
            afterEvaluate {
                extensions.configure<KotlinMultiplatformExtension> {
                    sourceSets.getByName("commonMain") {
                        dependencies {
                            // Coroutines
                            implementation(libs.findLibrary("kotlinx-coroutines-core-multiplatform").get())
                            implementation(libs.findLibrary("kotlinx-datetime").get())
                        }
                    }
                    
                    sourceSets.getByName("commonTest") {
                        dependencies {
                            implementation(libs.findLibrary("junit").get())
                            implementation(libs.findLibrary("kotlinx-coroutines-test").get())
                            implementation(libs.findLibrary("mockk").get())
                        }
                    }
                    
                    sourceSets.getByName("androidMain") {
                        dependencies {
                            // Android specific dependencies if needed
                        }
                    }
                }
            }
        }
    }
}

