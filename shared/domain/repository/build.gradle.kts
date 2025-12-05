plugins {
    id("kotlin-multiplatform-library-convention")
    id("kotlin-options-convention")
}

android {
    namespace = "com.jb.pixelquest.shared.domain.repository"
}

dependencies {
    implementation(project(":shared:domain:model"))
}

