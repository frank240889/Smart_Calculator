import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

fun main() {
    // write your code here
    val a = readln().toBigDecimal()
    val b = readln().toBigDecimal()
    val c = readln().toBigDecimal()
    val sum = a.plus(b).plus(c)
    val res = sum.divide(BigDecimal(3), 0, RoundingMode.DOWN)
    println(res)
}