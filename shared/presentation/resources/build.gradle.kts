plugins {
    id("com.android.library")
    id("android-library-convention")
    id("kotlin-options-convention")
}

android {
    namespace = "com.jb.pixelquest.shared.presentation.resources"

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

