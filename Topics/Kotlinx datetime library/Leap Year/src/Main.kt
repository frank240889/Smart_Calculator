import kotlinx.datetime.*
import java.time.Instant
import java.time.format.DateTimeParseException

fun isLeapYear(year: String): Boolean {
    // Write your code here
    return try {
        val instant = Instant.parse("$year-02-29T00:00:00Z")
        true
    } catch (e: DateTimeParseException) {
        false
    }
    //
}

fun main() {
    val year = readln()
    println(isLeapYear(year))
}