import java.math.BigDecimal
import java.math.RoundingMode

fun main() {             
    // write your code here
    val number = readln().toBigDecimal()
    val decimals = readln().toInt()
    println(number.setScale(decimals, RoundingMode.HALF_DOWN))

}