plugins {
    id("com.android.library")
    id("android-library-convention")
    id("kotlin-options-convention")
    id("kotlin-kapt")
}

android {
    namespace = "com.jb.pixelquest.core.di"
}

dependencies {
    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
