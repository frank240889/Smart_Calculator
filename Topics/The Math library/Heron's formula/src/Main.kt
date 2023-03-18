import kotlin.math.*

fun main() {
    // put your code here
    val a = readln().toInt()
    val b = readln().toInt()
    val c = readln().toInt()
    val p = p(a, b, c)
    val res = sqrt(p * (p - a) * (p - b) * (p - c))
    println(res)
}

private fun p(a: Int, b: Int, c: Int): Double {
    return (a + b + c) / 2.0
}