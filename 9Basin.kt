package Event4_AOC1

import java.io.File

fun main(args: Array<String>) {
    val reader = File(args[0])
    var above = listOf<Int>()
    var height = listOf<Int>()
    var below = listOf<Int>()

    var sum = 0
    var y = -1

    fun check(it: Int) {
        var test = true
        if(test && above.isNotEmpty())
            test = height[it] < above[it]
        if(test && below.isNotEmpty())
            test = height[it] < below[it]
        if(test && it < height.size - 1)
            test = height[it] < height[it+1]
        if(test && it > 1)
            test = height[it] < height[it-1]

        if(test) {
            //print("$it:$y ")
            sum += height[it] + 1
        }
    }

    reader.forEachLine { line ->
        below = line.toCharArray().map { it.digitToInt() }

        if(height.isNotEmpty())
            (height.indices).forEach { check(it) }
        above = height
        height = below
        y++
    }

    below = listOf()
    (height.indices).forEach { check(it) }

    println()
    println(sum)
}