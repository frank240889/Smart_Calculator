fun main() {
    val naturalNumbersUpToTwoHundredMillion =
        generateSequence(seed = 1) { previousNumber ->
            if (previousNumber < 200_000_000) {
                previousNumber + 1
            } else {
                null
            }
        }

    val firstHundredEvenNaturalNumbers = naturalNumbersUpToTwoHundredMillion
        .filter { number -> number % 2 == 0 } // 1
        .take(100)

    println(firstHundredEvenNaturalNumbers)
}