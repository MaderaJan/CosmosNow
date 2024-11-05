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

rootProject.name = "CosmosNow"
include(":webservice")
include(":database")

include(":data:model")
include(":data:repository")

include(":domain")

include(":core:designsystem")
include(":core:navigation")
include(":core:viewmodel")

include(":feature:news")
include(":feature:search")
include(":feature:newsdetail")
include(":feature:bookmarks")

include(":app")
include(":core:common")
