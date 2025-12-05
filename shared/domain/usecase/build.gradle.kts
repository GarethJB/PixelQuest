plugins {
    id("kotlin-multiplatform-library-convention")
    id("kotlin-options-convention")
}

android {
    namespace = "com.jb.pixelquest.shared.domain.usecase"
}

dependencies {
    implementation(project(":shared:domain:model"))
    implementation(project(":shared:domain:repository"))
}
