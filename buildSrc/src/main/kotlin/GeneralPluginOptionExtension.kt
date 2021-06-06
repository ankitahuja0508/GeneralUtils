import org.gradle.api.Action

class RetrofitOptions{
    var isEnabled : Boolean = true
}
class HiltOptions{
    var isEnabled : Boolean = true
}
open class GeneralPluginOptionExtension{
    val retrofit : RetrofitOptions = RetrofitOptions()
    fun retrofit(action: Action<RetrofitOptions>){
        action.execute(retrofit)
    }

    val hilt : HiltOptions = HiltOptions()
    fun hilt(action: Action<HiltOptions>){
        action.execute(hilt)
    }
}