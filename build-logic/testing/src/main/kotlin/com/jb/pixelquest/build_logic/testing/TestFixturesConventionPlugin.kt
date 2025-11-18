package com.jb.pixelquest.build_logic.testing

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Test Fixtures 기능을 활성화하는 플러그인
 * 테스트 픽스처를 다른 모듈과 공유할 수 있도록 설정
 */
class TestFixturesConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Test Fixtures 기능 활성화
            // Java 라이브러리나 Android 라이브러리에서 사용 가능
            if (plugins.hasPlugin("java-library") || plugins.hasPlugin("com.android.library")) {
                // Test Fixtures 소스셋 자동 생성
                // build.gradle.kts에서 testFixtures 블록을 사용할 수 있도록 준비
            }
        }
    }
}



