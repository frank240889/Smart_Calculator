import kotlin.math.pow

fun main() {
    val radius = readln().toDouble()
    println(String.format("%f", 3.1415.times(radius.pow(2))))
}