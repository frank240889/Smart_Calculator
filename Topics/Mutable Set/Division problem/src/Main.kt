fun solution(first: Set<Int>, second: Set<Int>): Set<Int> {
    val divisor = second.size
    val res = mutableSetOf<Int>()
    first.forEach {
        if (it.mod(divisor) == 0)
            res.add(it)
    }
    return res.toSet()
}