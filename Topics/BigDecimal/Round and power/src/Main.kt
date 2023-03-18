import java.math.BigDecimal
import java.math.RoundingMode     

fun main() {
    // write your code here
    val power = readln().toInt()
    val decimals = readln().toInt()
    val aBigDecimal = readln().toBigDecimal()
    val bigDecimalRounded = aBigDecimal.setScale(decimals, RoundingMode.FLOOR)
    println(bigDecimalRounded.pow(power))
}