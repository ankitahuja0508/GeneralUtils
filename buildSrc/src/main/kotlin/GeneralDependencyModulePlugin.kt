import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.dependencies

class GeneralDependencyModulePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // Adds your configuration code here.
        project.plugins.apply("kotlin-android")
        project.plugins.apply("kotlin-kapt")
        project.plugins.apply("kotlin-parcelize")
        project.plugins.apply("androidx.navigation.safeargs.kotlin")

        project.extensions.create<GeneralPluginOptionExtension>("generalPluginOptions")

        project.addRetrofitDependencies()

        project.addHiltDependencies()
        
        project.addKaptDependencies()

        // Adds required dependencies for all modules.

        project.dependencies {

            add("api" , "org.jetbrains.kotlin:kotlin-stdlib:1.4.32")
            add("api" , "androidx.core:core-ktx:1.3.2")
            add("api" , "androidx.appcompat:appcompat:1.2.0")
            add("api" , "com.google.android.material:material:1.3.0")
            add("api" , "androidx.constraintlayout:constraintlayout:2.0.4")
            add("api" , "androidx.annotation:annotation:1.2.0")
            add("api" , "androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
            add("api" , "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
            add("api" , "androidx.legacy:legacy-support-v4:1.0.0")

            add("api" , "androidx.activity:activity-ktx:1.2.3")

            add("api" , "androidx.fragment:fragment-ktx:1.3.3")

            add("api" , "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")

            add("api" , "androidx.navigation:navigation-fragment-ktx:2.3.5")
            add("api" , "androidx.navigation:navigation-ui-ktx:2.3.5")
            // Feature module Support
            add("api" , "androidx.navigation:navigation-dynamic-features-fragment:2.3.5")

            // Coroutines
            add("api" , "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
            add("api" , "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")

            //Epoxy
            add("api" , "com.airbnb.android:epoxy:4.4.4")
            add("api" , "com.airbnb.android:epoxy-databinding:4.4.4")

            add("api" , "com.google.code.gson:gson:2.8.6")

            add("api" , "com.github.bumptech.glide:glide:4.12.0")

            add("api" , "androidx.preference:preference-ktx:1.1.1")

            // Moshi
            add("api" , "com.squareup.moshi:moshi-kotlin:1.12.0")
            add("api" , "com.squareup.moshi:moshi-adapters:1.12.0")

        }
    }
}