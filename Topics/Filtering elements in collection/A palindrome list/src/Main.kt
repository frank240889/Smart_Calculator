/* Do not change code below */
fun main() {
    val list = readln().split(" ")
    // write your code here
    val res = list.filter { it.reversed() == it }
    println(res)
}