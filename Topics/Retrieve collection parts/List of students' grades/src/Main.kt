fun main() {
    val words = listOf("peter", "anne", "michael", "caroline")

    // define a comparator for the list of words
    val wordsLengthComparator = Comparator { str1: String, str2: String -> str1.length - str2.length }
    // Ordered list according to the comparator
    println(words.sortedWith(wordsLengthComparator)) // [anne, peter, michael, caroline]

    // another comparator using the middle letter
    val middleLetterComparator =
        Comparator { str1: String, str2: String -> str1[str1.length / 2] - str2[str2.length / 2] }

    // Ordered list according to the comparator
    println(words.sortedWith(middleLetterComparator)) // [michael, caroline, anne, peter]
}