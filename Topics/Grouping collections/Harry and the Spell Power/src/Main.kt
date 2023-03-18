data class Spell(val name: String, val power: Int)

fun main() {
    val listStrings = listOf("one", "two", "three", "four", "five")

    // Associate With
    val associatedWith = listStrings.associateWith { it.length }
    println(associatedWith) // {one=3, two=3, three=5, four=4, five=4}

    // Associate By
    val associatedBy = listStrings.associateBy { it.first().uppercase() }
    println(associatedBy) // {O=one, T=two, F=four, F=five}

    // Associate with a lambda
    val associatedWithLambda = listStrings.associate {
        it.first().uppercase() to it.length
    }
    println(associatedWithLambda) // {O=3, T=3, F=4, F=4}
}