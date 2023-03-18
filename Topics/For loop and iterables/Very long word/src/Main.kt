fun findLongestByIterator(strIterator: Iterator<String>): String {
    // write your code here
    var currentLongestString = ""
    while (strIterator.hasNext()) {
        val next = strIterator.next()
        if (next.length > currentLongestString.length) {
            currentLongestString = next
        }

    }
    return currentLongestString
}

fun main() {
    val words: List<String> = readln().split("\\s+".toRegex())
    println(findLongestByIterator(words.iterator()))
}