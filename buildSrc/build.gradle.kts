plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("general-plugin") {
            id = "general-plugin"
            implementationClass = "GeneralDependencyModulePlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    compileOnly(gradleApi())

    api("com.android.tools.build:gradle:4.2.1")
    api(kotlin("gradle-plugin", "1.4.32"))
    api("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.5")
}