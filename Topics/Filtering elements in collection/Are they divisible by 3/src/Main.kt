fun main() {
    val list = readln().split(" ").map { it.toInt() }
    // write your code here
    val res = list.filter { it.mod(3) == 0 }
    println(res)
}