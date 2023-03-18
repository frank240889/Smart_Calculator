data class Ship(val name: String, val ammunition: Int)

fun main() {
    val ships = readln().split(" ")
    val shipsList = ships.map { Ship(it.split("-")[0], it.split("-")[1].toInt()) }

    // write your code here
    val res = shipsList.filter { it.name.first() == 'T' }.map { it.ammunition }.fold(0) { acc, s ->
        if (s > 20)
            s + acc
        else
            acc
    }


    println(res)
}