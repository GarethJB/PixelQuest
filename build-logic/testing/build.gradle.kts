plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(project(":convention"))
}

gradlePlugin {
    plugins {
        register("test-configurations-convention") {
            id = "test-configurations-convention"
            implementationClass = "com.jb.pixelquest.build_logic.testing.TestConfigurationsConventionPlugin"
        }
        register("unit-test-convention") {
            id = "unit-test-convention"
            implementationClass = "com.jb.pixelquest.build_logic.testing.UnitTestConventionPlugin"
        }
        register("android-test-convention") {
            id = "android-test-convention"
            implementationClass = "com.jb.pixelquest.build_logic.testing.AndroidTestConventionPlugin"
        }
        register("test-fixtures-convention") {
            id = "test-fixtures-convention"
            implementationClass = "com.jb.pixelquest.build_logic.testing.TestFixturesConventionPlugin"
        }
    }
}