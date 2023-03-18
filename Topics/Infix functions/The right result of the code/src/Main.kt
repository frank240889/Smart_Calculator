// You can experiment here, it won’t be checked

fun main(args: Array<String>) {
    println("Hi, Kotlin" matches ".*Kotlin".toRegex() xor ("Kotlin".length > 4))
}
