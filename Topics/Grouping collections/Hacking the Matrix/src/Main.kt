fun main() {
     val list = readln().split(" ")
    // write your code here
    val res = list
        .groupingBy { it.length }
        .aggregate { _, accumulator: Int? , element, first ->
            if (first)
                1
            else
                accumulator!! + 1
        }


    println(res)
}