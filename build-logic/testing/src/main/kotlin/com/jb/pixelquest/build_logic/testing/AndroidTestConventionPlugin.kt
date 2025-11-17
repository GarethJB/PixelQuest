package com.jb.pixelquest.build_logic.testing

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Android Instrumentation Test에 필요한 의존성과 설정을 자동으로 추가하는 플러그인
 */

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                // Android JUnit
                add("androidTestImplementation", libs.findLibrary("androidx-junit").get())
                
                // Espresso
                add("androidTestImplementation", libs.findLibrary("androidx-espresso-core").get())
                
                // Test Runner & Rules
                add("androidTestImplementation", libs.findLibrary("androidx-test-runner").get())
                add("androidTestImplementation", libs.findLibrary("androidx-test-rules").get())
                
                // Compose Testing
                add("androidTestImplementation", platform(libs.findLibrary("androidx-compose-bom").get()))
                add("androidTestImplementation", libs.findLibrary("androidx-compose-ui-test-junit4").get())
                add("debugImplementation", libs.findLibrary("androidx-compose-ui-test-manifest").get())
            }
            
            // Android Test 태스크 설정
            tasks.named("connectedAndroidTest") {
                // Android 테스트 실행 시 설정
            }
        }
    }
}

