plugins {
    id("com.android.library")
    id("android-library-convention")
    id("kotlin-options-convention")
    id("org.jetbrains.kotlin.kapt")
    id("data-layer-dependency")
    id("database-dependency")
}

android {
    namespace = "com.jb.pixelquest.data.local.android"

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
    // Gson for JSON serialization in Room TypeConverters
    implementation(libs.gson)
}

