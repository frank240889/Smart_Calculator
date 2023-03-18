fun main() {
    val report = readLine()!!
    //write your code here.
    val regex = Regex("""[0-9] wrong answer(s)?""")
    println(regex.matches(report))
}