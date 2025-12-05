pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
                includeGroup("com.google.dagger")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PixelQuest"

// App
include(":app")

// Core
include(":core:common")
include(":core:di")

// Shared - Domain
include(":shared:domain:model")
include(":shared:domain:repository")
include(":shared:domain:usecase")

// Shared - Data
include(":shared:data:remote")
include(":shared:data:local")

// Shared - Presentation (Resources only - KMP compatible)
include(":shared:presentation:resources")

// Presentation (Android Compose - Platform specific)
include(":presentation:theme")
include(":presentation:component")
include(":presentation:navigation")


// Feature
include(":feature:studio")
include(":feature:quest")
include(":feature:gallery")
include(":feature:mypage")

// Test
include(":test:mock")
include(":test:fixture")
include(":test:rule")
include(":test:shared")
