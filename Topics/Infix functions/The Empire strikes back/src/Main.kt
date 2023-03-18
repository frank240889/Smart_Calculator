// complete the definition
infix fun List<Ship>.battle(ammunitionLimit: Int): List<String> {
    // write the rest of the code
    val ships = mutableListOf<String>()
    for (ship in this) {
        if (ship.ammunition > ammunitionLimit) {
            ships.add(ship.name)
        }
    }
    return ships.toList()
}