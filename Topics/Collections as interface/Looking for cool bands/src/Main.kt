fun main() {
    val listOfDishes = readln().split(", ").toList()
    val setOfDishes = listOfDishes.toSet()
    val keyWord = readln()

    println(findBands(listOfDishes, keyWord))
    println(findBands(setOfDishes, keyWord))
}

// Write here function findBands()

private fun findBands(dataset: Collection<String>, keyword: String): Collection<String> {
    return dataset.filter { it.lowercase().contains(keyword.lowercase()) }
}