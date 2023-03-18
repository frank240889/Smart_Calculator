// define the function
operator fun List<Int>.invoke(code: Int): Int {
    // and write your code here
    return this.filter { it.mod(code) == 0 }.sum()
}
