data class User(val name: String, val age: Int, val email: String)

class UserComparator : Comparator<User> {
    // write your code here
    override fun compare(o1: User?, o2: User?): Int {
        val res = (o1?.age ?: 0) - (o2?.age ?: 0)
        return if (res == 0) {
            naturalOrder<String>().compare(o1?.name ?: "", o2?.name ?: "")
        } else {
            res
        }
    }
}