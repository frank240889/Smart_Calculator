/* Do not change code below */
fun main() {
    val list = readln().split(" ")
    // write your code here
    val res = list
        .firstNotNullOf {
            it.takeIf {
                it.reversed().first() == 'm'
            }?.reversed()
        }
    println(res)
}