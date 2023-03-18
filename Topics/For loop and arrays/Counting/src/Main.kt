fun main() {
    // write your code here
    val data = IntArray(readln().toInt())
    for (i in data.indices) {
        data[i] = readln().toInt()
    }
    val n = readln().toInt()
    val times = data.filter { it == n }
    println(times.size)
}