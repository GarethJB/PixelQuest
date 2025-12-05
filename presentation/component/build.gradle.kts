plugins {
    id("com.android.library")
    id("android-library-convention")
    alias(libs.plugins.kotlin.compose)
    id("compose-convention")
    id("kotlin-options-convention")
    id("presentation-layer-dependency")
}

android {
    namespace = "com.jb.pixelquest.presentation.component"

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
    implementation(project(":presentation:theme"))
    implementation(project(":shared:presentation:resources"))
}
