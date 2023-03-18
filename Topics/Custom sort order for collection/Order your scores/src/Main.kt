fun main() {
    val list = readln().split(" ").map { it.toInt() }
    // write your code here
    val res = list.sortedBy {
        it.mod(2) == 0
    }
    println(res)
}