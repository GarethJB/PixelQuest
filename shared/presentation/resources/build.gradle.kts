plugins {
    alias(libs.plugins.kotlin.multiplatform)
    id("com.android.library")
    // android-library-convention은 kotlin-android를 적용하므로 KMP 모듈에서는 사용하지 않음
    id("kotlin-options-convention")
    // Compose Multiplatform Resources는 플러그인 없이 라이브러리만 사용
}

android {
    namespace = "com.jb.pixelquest.shared.presentation.resources"
    
    compileSdk = libs.versions.compileSdk.get().toInt()
    
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    
    compileOptions {
        sourceCompatibility = org.gradle.api.JavaVersion.VERSION_11
        targetCompatibility = org.gradle.api.JavaVersion.VERSION_11
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

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            // Compose Multiplatform Resources는 향후 KMP 확장 시 사용 예정
            // 현재는 Android 리소스 시스템 사용
        }
        
        val androidMain by getting {
            // Android 리소스는 src/main/res에서 계속 사용 가능
        }
    }
}

