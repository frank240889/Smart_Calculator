data class Musician(var name: String, var instrument: String = "Guitar", var band: String = "Radiohead")

fun main() {
    Musician("Jonny Greenwood").apply {
        instrument = "harmonica" // here we can use this.instrument
        band = "Pavement"

    }

    Musician("Jonny Greenwood").also {
        it.instrument = "harmonica"
    }

    Musician("Dave Glowl", "Drums", "Nirvana").let {
        it.name.length +it.instrument.length + it.band.count { it == 'a' }
    }
    // With it, we can pass several parameters and use them as separate parameter members. We can also rename these parameters
}
