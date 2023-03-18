fun main() {
    //Do not touch code below
    var inputArray: Array<Array<String>> = arrayOf()
    val n = readln().toInt()
    for (i in 0 until n) {
        val strings = readln().split(' ').toTypedArray()
        inputArray += strings
    }
    //write your code here
    println("${inputArray.first().first()} ${inputArray.first().last()}")
    println("${inputArray.last().first()} ${inputArray.last().last()}")
}