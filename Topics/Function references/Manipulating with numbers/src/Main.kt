fun multiplyByTwo(number: Int): Int {
    return number * 2
}

fun addTen(number: Int): Int {
    return number + 10
}

fun changeNumber(changeFunction: (Int) -> Int, number: Int) {
    val newNumber = changeFunction(number)
    print("$newNumber ")
}

fun main() {
    val numbers = readln().split(' ').map { it.toInt() }.toList()
    var numberFun: (Int) -> Int = { if (it.mod(2) > 0) multiplyByTwo(it) else addTen(it) }
    for (number in numbers) {
        // write your code here
        changeNumber(numberFun, number)
    }
}