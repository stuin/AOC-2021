package Event4_AOC

import java.io.File

fun main(args: Array<String>) {
    val reader = File(args[0])
    val points: MutableSet<Pair<Int, Int>> = mutableSetOf()
    val intersections: MutableList<Pair<Int, Int>> = mutableListOf()

    fun check(p: Pair<Int, Int>) {
        if(points.contains(p) && !intersections.contains(p))
            intersections.add(p)
        else
            points.add(p)
    }

    reader.forEachLine { line ->
        val ends = line.split(" -> ")
        var startX = ends[0].split(",")[0].toInt()
        var startY = ends[0].split(",")[1].toInt()
        var endX = ends[1].split(",")[0].toInt()
        var endY = ends[1].split(",")[1].toInt()

        if(startX >= endX && startY >= endY) {
            val x = startX
            val y = startY
            startX = endX
            startY = endY
            endX = x
            endY = y
        }

        if(startX == endX)
            (startY..endY).forEach { check(Pair(startX, it)) }
        else if(startY == endY)
            (startX..endX).forEach { check(Pair(it, startY)) }
        else if(startX - endX == startY - endY)
            (0..endX - startX).forEach { check(Pair(startX + it, startY + it)) }
        else if(startX - endX == endY - startY && startX < endX)
            (0..endX - startX).forEach { check(Pair(startX + it, startY - it)) }
        else if(startX - endX == endY - startY && startX > endX)
            (0..startX - endX).forEach { check(Pair(endX + it, endY - it)) }
        else
            println("$startX..$endX ! $startY..$endY")

    }

    println(points.size)
    println(intersections.size)
}