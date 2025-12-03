plugins {
    id("com.android.library")
    id("android-library-convention")
    id("kotlin-options-convention")
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.jb.pixelquest.core.di"
}

dependencies {
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}
