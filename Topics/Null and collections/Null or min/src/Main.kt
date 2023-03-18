fun main() {
    val words = readln().split(" ")
    val res = words.filter { Regex("[a-l].*").matches(it) }.minByOrNull { it.length }
    println(res)
}