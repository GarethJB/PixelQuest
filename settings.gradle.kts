pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
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
include(":app")
include(":data:remote")
include(":data:model")
include(":data:store")
include(":domain:model")
include(":domain:usecase")
include(":domain:repository")
include(":feature:home")
include(":presentation:resources")
include(":presentation:theme")
include(":presentation:component")
include(":presentation:navigation")
include(":presentation:viewmodel")
include(":core:common")
include(":core:di")
include(":test:mock")
include(":test:fixture")
include(":test:rule")
include(":test:shared")
