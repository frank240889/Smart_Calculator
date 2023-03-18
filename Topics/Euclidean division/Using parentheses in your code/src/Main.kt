fun main() {

    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val words = listOf("anne", "michael", "caroline", "dimitry", "emilio")

    println(words.maxWithOrNull(compareBy { it.length > 5 })) // caroline
    println(words.minWithOrNull(compareBy { it.length > 5 })) // anne
}