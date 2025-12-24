plugins {
    id("com.android.library")
    id("android-library-convention")
    alias(libs.plugins.kotlin.compose)
    id("compose-convention")
    id("kotlin-options-convention")
    id("presentation-layer-dependency")
}

android {
    namespace = "com.jb.pixelquest.feature.studio"

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

    implementation(project(":shared:domain:model"))
    implementation(project(":shared:domain:repository"))
    implementation(project(":shared:domain:usecase"))

    implementation(project(":shared:presentation:resources"))
    implementation(project(":presentation:component"))
    implementation(project(":presentation:theme"))

    implementation(libs.kotlinx.coroutines.core)
}


