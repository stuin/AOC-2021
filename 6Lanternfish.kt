package Event4_AOC1

import java.io.File
import java.math.BigInteger

fun main(args: Array<String>) {
    val reader = File(args[0]).bufferedReader()
    val days = 256

    val line = reader.readLine().split(",").map { it.toInt() }
    val fish = (0..8).map { days ->
        line.filter { days == it }.size.toBigInteger()
    }.toMutableList()

    (0 until days).forEach { day ->
        //print("day $day: ")
        //fish.forEach { print("$it ") }
        //println()

        val ready = fish[0]
        (0 until fish.size-1).forEach { fish[it] = fish[it + 1] }
        fish[6] += ready
        fish[8] = ready
    }

    fish.forEach { print("$it ") }
    println()

    var sum = BigInteger.ZERO
    fish.forEach { sum += it }
    println(sum)
}