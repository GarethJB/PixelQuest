package com.jb.pixelquest.build_logic.testing

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.withType

/**
 * 테스트 설정을 표준화하는 플러그인
 * 모든 테스트 관련 설정을 일관되게 관리
 */
class TestConfigurationsConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // 테스트 태스크 기본 설정
            tasks.withType<Test> {
                // 테스트 실행 시 표준 출력 사용
                testLogging {
                    events("passed", "skipped", "failed")
                    exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
                    showStandardStreams = true
                }
            }
        }
    }
}
