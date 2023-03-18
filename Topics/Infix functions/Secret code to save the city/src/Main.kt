// complete the definition
infix fun List<String>.secret(limit: Int): Int {
    // write the rest of the code
    return filter { it.length > limit }.sumOf { it.length }
}