import java.util.*

fun main() {
    val list = readln().split(" ").toMutableList()
    val res =  mutableListOf<String>().apply {
        val iterator = list.listIterator()
        while(iterator.hasNext()) {
            iterator.next()
        }
        while(iterator.hasPrevious()) {
            add(iterator.previous())
        }
    }

    // write your code here

    println(res)
}