import kotlin.math.*

fun main() {
    val v = readln().split(" ").map { it.toDouble() }
    val u = readln().split(" ").map { it.toDouble() }
    val v1 = v[0]
    val v2 = v[1]
    val u1 = u[0]
    val u2 = u[1]
    val lengthV = sqrt((v1 * v1) + (v2 * v2))
    val lengthU = sqrt((u1 * u1) + (u2 * u2))
    val scalar = (v1 * u1) + (v2 * u2)

    val value = scalar / (lengthU * lengthV)
    val angle = Math.toDegrees(acos(value)).toInt()
    println(angle)
}