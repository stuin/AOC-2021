package Event4_AOC

import java.io.File

fun main(args: Array<String>) {
    val reader = File(args[0])

    var sum = 0

    reader.forEachLine { line ->
        var config = line.split(" | ")[0].split(" ")
        val output = line.split(" | ")[1].split(" ")
        val codes = mutableMapOf<Int, String?>()

        //Unique lengths
        codes[1] = config.find { it.length == 2 }
        codes[7] = config.find { it.length == 3 }
        codes[4] = config.find { it.length == 4 }
        codes[8] = config.find { it.length == 7 }

        //Length 6 deduction
        codes[9] = config.find { it.length == 6 && codes[4]?.all { c -> it.contains(c) } ?: false }
        codes[0] = config.find { it.length == 6 && codes[7]?.all { c -> it.contains(c) } ?: false && it != codes[9] }
        codes[6] = config.find { it.length == 6 && it != codes[9] && it != codes[0] }

        //Length 5 deduction
        codes[3] = config.find { it.length == 5 && codes[7]?.all { c -> it.contains(c) } ?: false }
        codes[5] = config.find { it.length == 5 && it.all { c -> codes[6]?.contains(c) ?: false } }
        codes[2] = config.find { it.length == 5 && it != codes[3] && it != codes[5] }

        val values = MutableList<Set<Char>>(10) { setOf() }
        codes.forEach { (key, s) -> values[key] = s?.toSet() ?: setOf() }

        val num = output.map { code -> values.indexOf(code.toSet()).toChar() + '0'.code }.joinToString("")
        println(num)
        sum += num.toInt()
    }

    println(sum)
}