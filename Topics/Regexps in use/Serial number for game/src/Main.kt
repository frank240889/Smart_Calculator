fun findSerialNumberForGame(listGames: List<String>) {
    val game = readln()

    listGames.forEach {
        val elements = it.split("@")
        if (elements[0] == game)
        println("Code for Xbox - ${elements[1]}, for PC - ${elements[2]}")
    }
}