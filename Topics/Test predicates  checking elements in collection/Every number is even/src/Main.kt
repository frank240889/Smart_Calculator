fun main() {
    val list = readln().split(" ").map { it.toInt() }
    // write your code here
    val res = list.all { it.mod(2) == 0 && it <= 20 }
    println(res)
}