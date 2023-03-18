import java.math.BigInteger

fun main() {
    // write your code here
    val a = readln().toBigInteger()
    val b = readln().toBigInteger()
    val sum = a + b
    val factor = BigInteger.valueOf(100L)
    val aPercentage = (factor * a).divide(sum)
    val bPercentage = (factor * b).divide(sum)

    println("$aPercentage% $bPercentage%")
}