fun main() {
    val list = readln().split(" ").map { it.toDouble() }
    // write your code here
    val min = list.minWithOrNull(compareBy { it })
    val max = list.maxWithOrNull(compareBy { it })
    val average = list.average()


    println("$max:$min:$average")
}