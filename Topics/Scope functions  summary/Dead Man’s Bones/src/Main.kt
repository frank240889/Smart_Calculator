@Suppress("CanBeVal")
fun main() {
    var number = 45       // Warning will disappear from here
    var anotherNumber = 5 // Warning will still appear here
    println(number)
    println(anotherNumber)
}
