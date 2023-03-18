class Book(val title: String, val author: String, val price: Double)

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // windows of 3, default step is 1
    println(list.windowed(3))
    // [[1, 2, 3], [2, 3, 4], [3, 4, 5], [4, 5, 6], [5, 6, 7], [6, 7, 8], [7, 8, 9], [8, 9, 10]]
    // windows of 3, step 3
    println(list.windowed(3, 3)) // [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
    // windows of 3, step 4, partial windows is false
    println(list.windowed(3, 4, false)) // [[1, 2, 3], [5, 6, 7]]
    // windows of 3, step 4, partial windows is true
    println(list.windowed(3, 4, true)) // [[1, 2, 3], [5, 6, 7], [9, 10]]
    // windows of 3, step 4, partial windows is true, the result is transformed with sum
    println(list.windowed(3, 4, true) { it.sum() }) // [6, 18, 19]
}