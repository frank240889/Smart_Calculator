fun main() {
    val list = readln().split(" ")
    // write your code here
    val res = list.associateBy { it.first().uppercase() }

    println(res)
}