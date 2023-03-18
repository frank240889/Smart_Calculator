fun main() {
    // write your code here
    val input = readln()
    val regex = "([A-Z]{1})(\\w*)(\\s[A-Z]{1}\\w*)?".toRegex()
    regex.findAll(input).forEach {
        println(it.value)
    }
}