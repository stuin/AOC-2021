package Event4_AOC1

import java.io.File

fun main(args: Array<String>) {
    val reader = File(args[0])
    var position = 0
    var depth = 0
    var aim = 0

    reader.forEachLine {
        val move = it.split(" ")[0]
        val distance = it.split(" ")[1].toInt()

        when(move) {
            "forward" -> { position += distance; depth += aim * distance }
            "up" -> aim -= distance
            "down" -> aim += distance
        }
    }

    println(position * depth)
}