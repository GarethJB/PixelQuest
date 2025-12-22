plugins {
    id("com.android.library")
    id("android-library-convention")
    id("kotlin-options-convention")
    id("data-layer-dependency")
    id("networking-dependency")
    alias(libs.plugins.kotlin.ksp)
}

// Hilt 플러그인 적용
apply(plugin = "dagger.hilt.android.plugin")

android {
    namespace = "com.jb.pixelquest.data.remote.android"

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
    implementation(project(":shared:data:remote"))
    implementation(project(":shared:domain:model"))
    implementation(project(":shared:domain:repository"))
    implementation(project(":data:local:android"))
    
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}
