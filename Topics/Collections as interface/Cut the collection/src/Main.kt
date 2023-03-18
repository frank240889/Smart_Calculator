import kotlin.math.roundToInt

fun main() {

}

open class Device(val type: String) {
    open fun getFullInfo(): String = "$type type"
}

open class InputDevice(type: String, val portsNumber: Int) : Device(type) {
    open fun getFullInfo(): String = "$portsNumber ports"
}
