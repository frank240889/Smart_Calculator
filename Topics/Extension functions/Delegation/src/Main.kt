fun main() {
    val triple = Triple(1, "I am", listOf(8.0, 9.0, 10))
    println(triple) // (1, one)
    val triple2 = Triple(1, "I am", listOf(8.0, 9.0, 10.0))


    println(triple == triple2)
}