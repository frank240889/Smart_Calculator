import kotlinx.datetime.*
import kotlin.math.abs

fun daysDifference(date1: String, date2: String): Int {
    // Write your code here
    val d1 = LocalDate.parse(date1)
    val d2 = LocalDate.parse(date2)
    //
    return abs(d1.until(d2, DateTimeUnit.DAY))
}

fun main() {
    val date1 = readLine()!!
    val date2 = readLine()!!
    println( daysDifference(date1, date2) )
}