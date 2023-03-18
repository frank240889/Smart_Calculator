fun main() {
    val a: String by lazy {
        val b: String by lazy {
            val c: String by lazy {
                print("d")
                "c"
            }
            print("b")
            c
        }
        print("a")
        b
    }
    print(a)
}