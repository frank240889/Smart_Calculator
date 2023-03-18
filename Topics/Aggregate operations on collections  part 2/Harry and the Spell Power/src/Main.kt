data class Spell(val name: String, val power: Int)

fun main() {
    val input = readln().split(" ")
    val spells = input.map { Spell(it.split("-")[0], it.split("-")[1].toInt()) }

    // write your code here
    val res1 = spells.maxByOrNull { it.power }
    val res2 = spells.minByOrNull { it.power }

    println("Most powerful: $res1-Least powerful: $res2")
}