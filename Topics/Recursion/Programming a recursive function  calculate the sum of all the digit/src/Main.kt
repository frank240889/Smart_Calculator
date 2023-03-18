fun digitSum(n: Int): Int {
    if (n == 0)
        return n

    val numbers = n.toString()
    val elements = numbers.toList()
    val last = elements.last().digitToInt()

    return last + digitSum(elements.dropLast(1).joinToString(separator = "").toIntOrNull() ?: 0)
}