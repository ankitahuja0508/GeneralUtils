import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltEpoxyModulePlugin : Plugin<Project> {
    override fun apply(project: Project) {

        project.plugins.apply("kotlin-android")
        project.plugins.apply("dagger.hilt.android.plugin")

        // Adds required dependencies for all modules.

        project.dependencies {


        }
    }
}