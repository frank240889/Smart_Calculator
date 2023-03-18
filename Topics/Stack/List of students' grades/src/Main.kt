import java.util.*
import kotlin.collections.ArrayDeque

fun main() {
    val list = readln().split(" ").map{ it.toDouble() }
    // write your code here
    val stack = Stack<Double>().apply {
        addAll(list)
    }
    while(!stack.empty()) {
        print(
            "${stack.pop()} "
        )
    }
}