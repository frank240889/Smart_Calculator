fun main() {
    // write your code here
    val data = IntArray(readln().toInt())
    for (i in data.indices) {
        data[i] = readln().toInt()
    }
    val numbers = readln().trim().replace(" ","")
    val sets = data.joinToString("")
    val contains = sets.contains(numbers) || sets.reversed().contains(numbers)

    println(
        if (contains) "NO"
        else "YES"
    )
}