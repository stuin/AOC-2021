package Event4_AOC1

import java.io.File

fun main(args: Array<String>) {
    val reader = File(args[0]).bufferedReader()

    var oxygen: List<String> = reader.lines().toArray().filterIsInstance<String>()
    var scrubber = oxygen

    var i = 0
    while(oxygen.size > 1) {
        val common = if(oxygen.filter { it[i] == '1' }.size >= oxygen.filter { it[i] == '0' }.size) '1' else '0'
        oxygen = oxygen.filter { it[i] == common }
        i++
    }

    i = 0
    while(scrubber.size > 1) {
        val common = if(scrubber.filter { it[i] == '1' }.size < scrubber.filter { it[i] == '0' }.size) '1' else '0'
        scrubber = scrubber.filter { it[i] == common }
        i++
    }

    println(oxygen[0])
    println(scrubber[0])
    println(oxygen[0].toInt(2) * scrubber[0].toInt(2))
}