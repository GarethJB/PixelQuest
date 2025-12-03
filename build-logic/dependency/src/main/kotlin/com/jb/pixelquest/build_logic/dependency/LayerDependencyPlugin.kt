package com.jb.pixelquest.build_logic.dependency

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.add

/**
 * Android 앱 모듈에 필요한 모든 의존성을 자동으로 설정하는 플러그인
 */
class AndroidAppDependencyPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                // Core Android
                addCoreAndroidDependencies(target)
                
                // Compose
                addComposeDependencies(target)
                
                // MVI 패턴
                addMviDependencies(target)
                
                // Navigation
                addNavigationDependencies(target)
                
                // Testing
                addTestingDependencies(target)
            }
        }
    }
    
    private fun org.gradle.api.artifacts.dsl.DependencyHandler.addCoreAndroidDependencies(project: Project) {
        add("implementation", project.libs.findLibrary("androidx-core-ktx").get())
        add("implementation", project.libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
        add("implementation", project.libs.findLibrary("androidx-activity-compose").get())
    }
    
    private fun org.gradle.api.artifacts.dsl.DependencyHandler.addComposeDependencies(project: Project) {
        add("implementation", this.platform(project.libs.findLibrary("androidx-compose-bom").get()))
        add("implementation", project.libs.findLibrary("androidx-compose-ui").get())
        add("implementation", project.libs.findLibrary("androidx-compose-ui-graphics").get())
        add("implementation", project.libs.findLibrary("androidx-compose-ui-tooling-preview").get())
        add("implementation", project.libs.findLibrary("androidx-compose-material3").get())
    }
    
    private fun org.gradle.api.artifacts.dsl.DependencyHandler.addMviDependencies(project: Project) {
        // Lifecycle
        add("implementation", project.libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
        add("implementation", project.libs.findLibrary("androidx-lifecycle-runtime-compose").get())
        
        // Orbit MVI
        add("implementation", project.libs.findLibrary("orbit-core").get())
        add("implementation", project.libs.findLibrary("orbit-viewmodel").get())
        add("implementation", project.libs.findLibrary("orbit-compose").get())
    }
    
    private fun org.gradle.api.artifacts.dsl.DependencyHandler.addNavigationDependencies(project: Project) {
        add("implementation", project.libs.findLibrary("androidx-navigation-compose").get())
    }
    
    private fun org.gradle.api.artifacts.dsl.DependencyHandler.addTestingDependencies(project: Project) {
        add("testImplementation", project.libs.findLibrary("junit").get())
        add("androidTestImplementation", project.libs.findLibrary("androidx-junit").get())
        add("androidTestImplementation", project.libs.findLibrary("androidx-espresso-core").get())
        add("androidTestImplementation", this.platform(project.libs.findLibrary("androidx-compose-bom").get()))
        add("androidTestImplementation", project.libs.findLibrary("androidx-compose-ui-test-junit4").get())
        add("debugImplementation", project.libs.findLibrary("androidx-compose-ui-tooling").get())
        add("debugImplementation", project.libs.findLibrary("androidx-compose-ui-test-manifest").get())
    }
}

/**
 * 데이터 레이어 모듈에 필요한 의존성을 자동으로 설정하는 플러그인
 */
class DataLayerDependencyPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                // Core Android
                add("implementation", libs.findLibrary("androidx-core-ktx").get())
                
                // Coroutines
                add("implementation", libs.findLibrary("kotlinx-coroutines-core").get())
                
                // Testing
                add("testImplementation", libs.findLibrary("junit").get())
                add("testImplementation", libs.findLibrary("mockk").get())
                add("testImplementation", libs.findLibrary("kotlinx-coroutines-test").get())
            }
        }
    }
}

/**
 * 도메인 레이어 모듈에 필요한 의존성을 자동으로 설정하는 플러그인
 */
class DomainLayerDependencyPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                // Coroutines
                add("implementation", libs.findLibrary("kotlinx-coroutines-core").get())
                
                // Testing
                add("testImplementation", libs.findLibrary("junit").get())
                add("testImplementation", libs.findLibrary("mockk").get())
                add("testImplementation", libs.findLibrary("kotlinx-coroutines-test").get())
            }
        }
    }
}

/**
 * 프레젠테이션 레이어 모듈에 필요한 의존성을 자동으로 설정하는 플러그인
 */
class PresentationLayerDependencyPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Hilt 플러그인 적용 (feature 모듈에서만 사용)
            if (target.path.contains(":feature:")) {
                pluginManager.apply("dagger.hilt.android.plugin")
            }
            
            dependencies {
                // Core Android
                add("implementation", libs.findLibrary("androidx-core-ktx").get())
                add("implementation", libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
                
                // Compose
                add("implementation", this.platform(target.libs.findLibrary("androidx-compose-bom").get()))
                add("implementation", libs.findLibrary("androidx-compose-ui").get())
                add("implementation", libs.findLibrary("androidx-compose-ui-graphics").get())
                add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
                add("implementation", libs.findLibrary("androidx-compose-material3").get())
                
                // MVI 패턴
                add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
                add("implementation", libs.findLibrary("androidx-lifecycle-runtime-compose").get())
                
                // Orbit MVI
                add("implementation", libs.findLibrary("orbit-core").get())
                add("implementation", libs.findLibrary("orbit-viewmodel").get())
                add("implementation", libs.findLibrary("orbit-compose").get())
                
                // Coil (이미지 로딩)
                add("implementation", libs.findLibrary("coil-compose").get())
                
                // Hilt
                add("implementation", libs.findLibrary("hilt-android").get())
                add("ksp", libs.findLibrary("hilt-compiler").get())
                
                // Testing
                add("testImplementation", libs.findLibrary("junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx-junit").get())
        add("androidTestImplementation", libs.findLibrary("androidx-espresso-core").get())
                add("androidTestImplementation", this.platform(target.libs.findLibrary("androidx-compose-bom").get()))
        add("androidTestImplementation", libs.findLibrary("androidx-compose-ui-test-junit4").get())
        add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
        add("debugImplementation", libs.findLibrary("androidx-compose-ui-test-manifest").get())
            }
        }
    }
}





