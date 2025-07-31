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
include(":common")
include(":ui:main")
include(":ui:settings")
include(":ui:getstarted")
include(":ui:help")
include(":ui:search")
include(":ui:splash")
include(":ui:whoiswatching")
include(":domain:getstarted")
include(":domain:help")
include(":domain:main")
include(":domain:search")
include(":domain:settings")
include(":domain:splash")
include(":domain:whoiswatching")
include(":data:main")
include(":data:getstarted")
include(":data:help")
include(":data:search")
include(":data:settings")
include(":data:splash")
include(":data:whoiswatching")
