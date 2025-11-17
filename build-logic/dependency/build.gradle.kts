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
        register("compose-dependency") {
            id = "compose-dependency"
            implementationClass = "com.jb.pixelquest.build_logic.dependency.ComposeDependencyPlugin"
        }
        register("networking-dependency") {
            id = "networking-dependency"
            implementationClass = "com.jb.pixelquest.build_logic.dependency.NetworkingDependencyPlugin"
        }
        register("database-dependency") {
            id = "database-dependency"
            implementationClass = "com.jb.pixelquest.build_logic.dependency.DatabaseDependencyPlugin"
        }
        register("testing-dependency") {
            id = "testing-dependency"
            implementationClass = "com.jb.pixelquest.build_logic.dependency.TestingDependencyPlugin"
        }
        register("dependency-validation") {
            id = "dependency-validation"
            implementationClass = "com.jb.pixelquest.build_logic.dependency.DependencyValidationPlugin"
        }
        register("dependency-update-check") {
            id = "dependency-update-check"
            implementationClass = "com.jb.pixelquest.build_logic.dependency.DependencyUpdateCheckPlugin"
        }
        register("android-app-dependency") {
            id = "android-app-dependency"
            implementationClass = "com.jb.pixelquest.build_logic.dependency.AndroidAppDependencyPlugin"
        }
        register("data-layer-dependency") {
            id = "data-layer-dependency"
            implementationClass = "com.jb.pixelquest.build_logic.dependency.DataLayerDependencyPlugin"
        }
        register("domain-layer-dependency") {
            id = "domain-layer-dependency"
            implementationClass = "com.jb.pixelquest.build_logic.dependency.DomainLayerDependencyPlugin"
        }
        register("presentation-layer-dependency") {
            id = "presentation-layer-dependency"
            implementationClass = "com.jb.pixelquest.build_logic.dependency.PresentationLayerDependencyPlugin"
        }
    }
}