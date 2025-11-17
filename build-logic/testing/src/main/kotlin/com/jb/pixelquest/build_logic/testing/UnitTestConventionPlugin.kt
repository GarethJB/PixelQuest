package com.jb.pixelquest.build_logic.testing

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Unit Test에 필요한 의존성과 설정을 자동으로 추가하는 플러그인
 */

class UnitTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                // JUnit
                add("testImplementation", libs.findLibrary("junit").get())
                
                // MockK
                add("testImplementation", libs.findLibrary("mockk").get())
                
                // Coroutines Test
                add("testImplementation", libs.findLibrary("kotlinx-coroutines-test").get())
            }
            
            // Unit Test 태스크 설정
            tasks.named("test", org.gradle.api.tasks.testing.Test::class.java) {
                // 테스트 로깅 설정
                testLogging {
                    events("passed", "skipped", "failed")
                    exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
                }
            }
        }
    }
}

