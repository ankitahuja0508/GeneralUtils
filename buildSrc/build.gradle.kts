plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("hilt-epoxy-plugin") {
            id = "hilt-epoxy-plugin"
            implementationClass = "HiltEpoxyModulePlugin"
        }
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

    implementation("com.android.tools.build:gradle:4.2.1")
    implementation(kotlin("gradle-plugin", "1.4.32"))
}