fun main() {
    // write your code here
    val a = readln().toBigInteger()
    val b = readln().toBigInteger()
    val (q, r) = a.divideAndRemainder(b)
    println("$a = $b * $q + $r")

}