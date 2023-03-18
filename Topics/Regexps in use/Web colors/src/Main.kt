fun main() {
    val text = readLine()!!
    val regexColors = "#[0-9a-fA-F]{6}\\b".toRegex()
    // write your code here
    regexColors
        .findAll(text)
        .let {
            val iterator = it.iterator()
            while (iterator.hasNext()) {
                println(iterator.next().value)
            }
        }
}
