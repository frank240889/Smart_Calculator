fun main() {
    val list = readln().split(" ").map { it.toInt() }

    // write your code here
    val res = mutableListOf<Int>()
    val arrayDeque = ArrayDeque(list)
    list.forEachIndexed() { index, element ->
        res.add(
            if (index.mod(2) == 0) {
                arrayDeque.removeFirst()
            } else {
                arrayDeque.removeLast()
            }
        )
    }

    println(
        res.joinToString(separator = " ")
    )
}

/*1 2 3 8 10 10

0 1 2 3 4 5 6

1
10
2
10*/

