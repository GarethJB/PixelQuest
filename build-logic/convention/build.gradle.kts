plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.hilt.android.gradle.plugin)
    // Android Gradle Plugin API for using Android extensions in build logic
    implementation("com.android.tools.build:gradle:8.12.0")
    // Kotlin Gradle Plugin API for using Kotlin extensions in build logic
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.21")
}

gradlePlugin {
    plugins {
        register("android-application-convention") {
            id = "android-application-convention"
            implementationClass = "com.jb.pixelquest.build_logic.convention.AndroidApplicationConventionPlugin"
        }
        register("android-library-convention") {
            id = "android-library-convention"
            implementationClass = "com.jb.pixelquest.build_logic.convention.AndroidLibraryConventionPlugin"
        }
        register("kotlin-library-convention") {
            id = "kotlin-library-convention"
            implementationClass = "com.jb.pixelquest.build_logic.convention.KotlinLibraryConventionPlugin"
        }
        register("compose-convention") {
            id = "compose-convention"
            implementationClass = "com.jb.pixelquest.build_logic.convention.ComposeConventionPlugin"
        }
        register("kotlin-options-convention") {
            id = "kotlin-options-convention"
            implementationClass = "com.jb.pixelquest.build_logic.convention.KotlinOptionsConventionPlugin"
        }
        register("kotlin-multiplatform-library-convention") {
            id = "kotlin-multiplatform-library-convention"
            implementationClass = "com.jb.pixelquest.build_logic.convention.KotlinMultiplatformLibraryConventionPlugin"
        }
    }
}
