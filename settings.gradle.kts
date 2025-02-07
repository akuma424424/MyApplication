pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS) // Esto es importante
    repositories {
        google() // Repositorio de Google declarado aquí
        mavenCentral()
    }
}

rootProject.name = "MyApplication1"
include(":app")
include(":mylibrary") // Si tienes un módulo de biblioteca