fun processIterator(list: List<String>): List<String> {
    val mutableList = list.toMutableList()
    val iterator = mutableList.listIterator()
    while (iterator.hasNext()) iterator.next()

    while(iterator.hasPrevious()) {
        val element = iterator.previous()
        if (!element.startsWith("hyper")) {
            iterator.remove()
        }
        else {
            println(
                element
            )
        }
    }
    return mutableList.toList()
}

fun main() {
    var list = processIterator(readln().split(" "))
}