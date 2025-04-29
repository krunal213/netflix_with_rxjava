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
        maven { url = uri("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "NetflixWithRxJava"
include(":app")
include(":android_resource")
include(":feature:ui:settings")
include(":feature:ui:getstarted")
include(":feature:ui:help")
include(":feature:ui:main")
include(":feature:ui:search")
include(":feature:ui:splash")
include(":feature:ui:whoiswatching")
