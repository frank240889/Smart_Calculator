fun main() {
    // write your code here
    val input = readln()
    val regex = "(\\(?[0-8]{3}\\)?-?)([0-8]{3}-?)([0-8]{4})"
    printPhones(extractPhones(input,regex.toRegex()))
}

private fun extractPhones(data: String, regex: Regex): List<String> {
    return regex.findAll(data).toList().map { it.value }
}

private fun printPhones(phones: List<String>) {
    phones.forEach {
        println(it)
    }
}