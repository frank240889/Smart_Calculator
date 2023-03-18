fun main() {
    val domains = readln().split(" ")

    val map = with(domains) {
        println(size)
        associateBy (
            { it.lowercase() } , {"[" + it.length + "]"}
        )
    }

    println(map)    
}