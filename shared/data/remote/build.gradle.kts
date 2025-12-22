plugins {
    id("kotlin-multiplatform-library-convention")
    id("kotlin-options-convention")
}

android {
    namespace = "com.jb.pixelquest.shared.data.remote"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:domain:model"))
            }
        }
        
        val androidMain by getting {
            dependencies {
                // Gson for DTO serialization annotations (compile-time only)
                compileOnly(libs.gson)
            }
        }
    }
}

