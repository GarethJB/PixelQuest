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
include(":presentation:theme")
include(":presentation:component")
include(":presentation:navigation")
include(":presentation:viewmodel")
include(":core:common")
include(":core:di")
include(":build-logic:convention")
include(":build-logic:dependency")
include(":build-logic:testing")
include(":test:mock")
include(":test:fixture")
include(":test:rule")
include(":test:shared")
