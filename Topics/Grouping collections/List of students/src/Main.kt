fun main() {
    val list = readln().split(" ")
    // write your code here
    val res = list
        .groupingBy(keySelector = { it.first() } )
        .aggregate { _, accumulator: String?, element, first ->
            if (first)
                element
            else if (accumulator!!.length > element.length)
                accumulator
            else
                element
        }


    println(res)
}