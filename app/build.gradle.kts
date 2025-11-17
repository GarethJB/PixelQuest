plugins {
    id("android-application-convention")
    id("android-app-dependency")
}

android {
    namespace = "com.jb.pixelquest"

    defaultConfig {
        applicationId = "com.jb.pixelquest"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Material Components (기존 테마에서 사용 중)
    implementation(libs.material)
    implementation(libs.androidx.appcompat)
}