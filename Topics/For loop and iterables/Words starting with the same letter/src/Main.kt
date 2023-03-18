fun main() {
    val words = readln().split(" ").toMutableList()
    // output the result collection
    val iterator = words.iterator()
    var currentChar: Char? = null
    while (iterator.hasNext()) {
        val next = iterator.next().first()
        if (currentChar == null) {
            currentChar = next
        }
        else if (currentChar != next) {
            words.clear()
            break
        }
    }

    println(
        words.joinToString(separator = " ")
    )
}