plugins {
    id("com.android.library")
    id("android-library-convention")
    alias(libs.plugins.kotlin.compose)
    id("compose-convention")
    id("kotlin-options-convention")
    id("presentation-layer-dependency")
}

android {
    namespace = "com.jb.pixelquest.presentation.navigation"

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
    // Feature Modules
    implementation(project(":feature:home"))
    implementation(project(":feature:studio"))
    implementation(project(":feature:quest"))
    implementation(project(":feature:gallery"))
    implementation(project(":feature:mypage"))

    // Shared Presentation Resources
    implementation(project(":shared:presentation:resources"))
    
    // Navigation Compose (currentBackStackEntryAsState 포함)
    implementation(libs.androidx.navigation.compose)
    // Navigation Runtime KTX
    implementation(libs.androidx.navigation.runtime.ktx)
}
