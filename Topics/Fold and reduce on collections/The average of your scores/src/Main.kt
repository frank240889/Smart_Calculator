fun main() {
    val list = readln().split(" ").map { it.toDouble() }
    // write your code here
    val res = list.reduceIndexed { index, acc, d ->
        if (index == list.lastIndex) {
            (acc + d) / list.size
        }
        else {
            acc + d
        }
    }
    println(res)
}