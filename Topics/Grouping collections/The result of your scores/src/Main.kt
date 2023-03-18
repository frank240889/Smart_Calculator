const val MINIMUM_MARK = 5
fun main() {
    val list = readln().split(" ").map { it.toDouble() }

    // write your code here
    val res = list
        .groupingBy{ if (it >= 5)"pass" else "fail" }
        .aggregate { _, accumulator: Int? , element, first ->
            if (first)
                1
            else
                accumulator!! + 1
        }


    println(res)
}