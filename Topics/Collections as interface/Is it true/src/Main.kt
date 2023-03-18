fun main() {
    val nameList = readln().split(" ").toMutableList()
    val nameSet = nameList.toMutableSet()
    val name = readln()

    println(checkElements(nameList, name))
    println(checkElements(nameSet, name))
}

// Write here function checkElements()
private fun checkElements(collection: Collection<String>, name: String): Boolean {
    return collection.contains(name)
}