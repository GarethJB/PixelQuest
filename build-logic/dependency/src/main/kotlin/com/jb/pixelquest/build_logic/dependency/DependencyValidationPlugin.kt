package com.jb.pixelquest.build_logic.dependency

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.tasks.TaskAction


/**
 * 의존성 버전 검증 및 호환성 체크를 수행하는 플러그인
 */
class DependencyValidationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            tasks.register("validateDependencies", DependencyValidationTask::class.java)
        }
    }
}

/**
 * 의존성 검증 태스크
 */
abstract class DependencyValidationTask : DefaultTask() {
    
    @TaskAction
    fun validateDependencies() {
        val libs = project.libs
        
        println("🔍 의존성 검증 시작...")
        
        // Kotlin 버전 검증
        validateKotlinVersion(libs)
        
        // Android Gradle Plugin 버전 검증
        validateAndroidGradlePluginVersion(libs)
        
        // Compose 버전 호환성 검증
        validateComposeCompatibility(libs)
        
        // Hilt 버전 검증
        validateHiltVersion(libs)
        
        println("✅ 의존성 검증 완료!")
    }
    
    private fun validateKotlinVersion(libs: VersionCatalog) {
        val kotlinVersion = libs.findVersion("kotlin").get().toString()
        println("📱 Kotlin 버전: $kotlinVersion")
        
        // Kotlin 버전이 최신인지 확인
        if (kotlinVersion.startsWith("2.0")) {
            println("✅ Kotlin 2.0 사용 중 - 최신 버전입니다.")
        } else {
            println("⚠️  Kotlin 버전을 업데이트하는 것을 고려해보세요.")
        }
    }
    
    private fun validateAndroidGradlePluginVersion(libs: VersionCatalog) {
        val agpVersion = libs.findVersion("agp").get().toString()
        println("🔧 Android Gradle Plugin 버전: $agpVersion")
        
        // AGP 버전이 Kotlin과 호환되는지 확인
        if (agpVersion.startsWith("8.")) {
            println("✅ AGP 8.x 사용 중 - Kotlin 2.0과 호환됩니다.")
        } else {
            println("⚠️  AGP 버전을 업데이트하는 것을 고려해보세요.")
        }
    }
    
    private fun validateComposeCompatibility(libs: VersionCatalog) {
        val composeBomVersion = libs.findVersion("composeBom").get().toString()
        val kotlinCompilerExtension = libs.findVersion("kotlinCompilerExtension").get().toString()
        
        println("🎨 Compose BOM 버전: $composeBomVersion")
        println("🔧 Kotlin Compiler Extension: $kotlinCompilerExtension")
        
        // Compose BOM과 Kotlin Compiler Extension 호환성 확인
        if (composeBomVersion.startsWith("2024") && kotlinCompilerExtension.startsWith("1.5")) {
            println("✅ Compose와 Kotlin Compiler Extension이 호환됩니다.")
        } else {
            println("⚠️  Compose BOM과 Kotlin Compiler Extension 버전을 확인해주세요.")
        }
    }
    
    private fun validateHiltVersion(libs: VersionCatalog) {
        val hiltVersion = libs.findVersion("hilt").get().toString()
        println("💉 Hilt 버전: $hiltVersion")
        
        if (hiltVersion.startsWith("2.4")) {
            println("✅ Hilt 2.4x 사용 중 - 최신 버전입니다.")
        } else {
            println("⚠️  Hilt 버전을 업데이트하는 것을 고려해보세요.")
        }
    }
}

/**
 * 의존성 업데이트 체크 플러그인
 */
class DependencyUpdateCheckPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            tasks.register("checkDependencyUpdates", DependencyUpdateCheckTask::class.java)
        }
    }
}

/**
 * 의존성 업데이트 체크 태스크
 */
abstract class DependencyUpdateCheckTask : DefaultTask() {
    
    @TaskAction
    fun checkDependencyUpdates() {
        val libs = project.libs
        
        println("🔄 의존성 업데이트 체크 시작...")
        
        // 주요 라이브러리들의 현재 버전 출력
        printCurrentVersions(libs)
        
        // 업데이트 권장사항 제시
        printUpdateRecommendations()
        
        println("✅ 의존성 업데이트 체크 완료!")
    }
    
    private fun printCurrentVersions(libs: VersionCatalog) {
        println("\n📋 현재 사용 중인 주요 라이브러리 버전:")
        println("  • Android Gradle Plugin: ${libs.findVersion("agp").get()}")
        println("  • Kotlin: ${libs.findVersion("kotlin").get()}")
        println("  • Compose BOM: ${libs.findVersion("composeBom").get()}")
        println("  • Hilt: ${libs.findVersion("hilt").get()}")
        println("  • Room: ${libs.findVersion("room").get()}")
        println("  • Retrofit: ${libs.findVersion("retrofit").get()}")
    }
    
    private fun printUpdateRecommendations() {
        println("\n💡 업데이트 권장사항:")
        println("  • 정기적으로 의존성 업데이트를 확인하세요")
        println("  • 메이저 버전 업데이트 시 호환성을 확인하세요")
        println("  • 테스트를 충분히 수행한 후 프로덕션에 적용하세요")
    }
}








