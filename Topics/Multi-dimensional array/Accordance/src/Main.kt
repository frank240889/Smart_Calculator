fun main() {
    // put your code here
    val data = mutableListOf<MutableList<String>>()
    for(i in 0 until 2) {
        val innerData = mutableListOf<String>()
        for (j in 0 until 3) {
            innerData.add("[$i][$j]")
        }
        data.add(innerData)
    }

    println(data.toTypedArray().contentDeepToString())
}
