package com.jb.pixelquest.build_logic.dependency

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Compose 의존성 그룹을 자동으로 추가하는 플러그인
 */
class ComposeDependencyPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                // Compose BOM
                add("implementation",
                    this.platform(libs.findLibrary("androidx-compose-bom").get())
                )
                
                // Core Compose
                add("implementation", libs.findLibrary("androidx-compose-ui").get())
                add("implementation", libs.findLibrary("androidx-compose-ui-graphics").get())
                add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
                add("implementation", libs.findLibrary("androidx-compose-material3").get())
                add("implementation", libs.findLibrary("androidx-compose-runtime").get())
                
                // Compose Testing
                add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
                add("androidTestImplementation", libs.findLibrary("androidx-compose-ui-test-junit4").get())
                add("debugImplementation", libs.findLibrary("androidx-compose-ui-test-manifest").get())
            }
        }
    }
}

/**
 * 네트워킹 의존성 그룹을 자동으로 추가하는 플러그인
 */
class NetworkingDependencyPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                // Retrofit
                add("implementation", libs.findLibrary("retrofit").get())
                add("implementation", libs.findLibrary("retrofit-converter-gson").get())
                
                // OkHttp
                add("implementation", libs.findLibrary("okhttp").get())
                add("implementation", libs.findLibrary("okhttp-logging-interceptor").get())
                
                // Testing
                add("testImplementation", libs.findLibrary("okhttp-mockwebserver").get())
            }
        }
    }
}

/**
 * 데이터베이스 의존성 그룹을 자동으로 추가하는 플러그인
 */
class DatabaseDependencyPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                // Room
                add("implementation", libs.findLibrary("androidx-room-runtime").get())
                add("implementation", libs.findLibrary("androidx-room-ktx").get())
                add("kapt", libs.findLibrary("androidx-room-compiler").get())
                
                // Testing
                add("testImplementation", libs.findLibrary("androidx-room-testing").get())
            }
        }
    }
}

/**
 * 테스팅 의존성 그룹을 자동으로 추가하는 플러그인
 */
class TestingDependencyPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                // Unit Testing
                add("testImplementation", libs.findLibrary("junit").get())
                add("testImplementation", libs.findLibrary("mockk").get())
                add("testImplementation", libs.findLibrary("kotlinx-coroutines-test").get())
                
                // Android Testing
                add("androidTestImplementation", libs.findLibrary("androidx-junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx-espresso-core").get())
                add("androidTestImplementation", libs.findLibrary("androidx-test-runner").get())
                add("androidTestImplementation", libs.findLibrary("androidx-test-rules").get())
            }
        }
    }
}





