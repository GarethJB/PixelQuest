plugins {
    id("com.android.library")
    id("android-library-convention")
    id("kotlin-options-convention")
    id("data-layer-dependency")
}

android {
    namespace = "com.jb.pixelquest.shared.data.local"

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
    implementation(project(":shared:domain:model"))
}

