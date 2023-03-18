fun main() {
    val answer = readln()
    println(Regex("I can('t)? do my homework on time!").matches(answer))
}