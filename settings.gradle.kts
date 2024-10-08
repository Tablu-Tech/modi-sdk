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
   // repositoriesMode = RepositoriesMode.PREFER_PROJECT
   repositories {
        google()
        mavenCentral()
        maven ("https://maven.regulaforensics.com/RegulaDocumentReader")
        maven ("https://jitpack.io")
    }
}

rootProject.name = "ModiSdkLibray"
include(":app")
include(":modisdk")
