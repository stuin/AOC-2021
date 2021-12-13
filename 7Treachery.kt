package Event4_AOC1

import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    val reader = File(args[0]).bufferedReader()

    val line = reader.readLine().split(",").map { it.toInt() }
    val min = line.minOrNull()
    val max = line.maxOrNull()

    if(min == null || max == null)
        return;

    val positions = (min..max).map { pos ->
        line.sumOf { (1..abs(it - pos)).sum() }
    }

    println(positions.minOrNull())
}