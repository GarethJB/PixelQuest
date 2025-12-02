plugins {
    id("com.android.library")
    id("android-library-convention")
    alias(libs.plugins.kotlin.compose)
    id("compose-convention")
    id("kotlin-options-convention")
    id("presentation-layer-dependency")
}

android {
    namespace = "com.jb.pixelquest.feature.home"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":core:common"))

    implementation(project(":domain:model"))
    implementation(project(":domain:repository"))
    implementation(project(":domain:usecase"))

    implementation(project(":presentation:resources"))
    implementation(project(":presentation:component"))
    implementation(project(":presentation:theme"))

    implementation(libs.kotlinx.coroutines.core)
}

