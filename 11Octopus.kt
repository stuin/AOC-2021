package Event4_AOC

import java.io.File

fun main(args: Array<String>) {
    val reader = File(args[0])
    var energy = mutableListOf<Int>()
    var height = 0
    var width = 0

    reader.forEachLine { line ->
        line.forEach { energy.add(it.digitToInt()) }
        height++
        width = line.length
    }

    (1..1000).forEach { step ->
        energy = energy.map { it + 1 }.toMutableList()

        val ready = energy.mapIndexed { index, value ->
            if(value > 9)
                index
            else
                null
        }.filterNotNull().toMutableList()

        var j = 0
        fun check(i: Int) {
            if(i >= 0 && i < energy.size) {
                energy[i]++
                if(energy[i] > 9 && !ready.contains(i))
                    ready.add(i)
            }
        }

        while(j < ready.size) {
            val i = ready[j]

            val left = i % width > 0
            val right = i % width < width - 1
            val up = i >= width
            val down = i < energy.size - width

            if(left)
                check(i - 1)
            if(right)
                check(i + 1)
            if(up)
                check(i - width)
            if(down)
                check(i + width)

            if(left && up)
                check(i - width - 1)
            if(right && up)
                check(i - width + 1)
            if(left && down)
                check(i + width - 1)
            if(right && down)
                check(i + width + 1)

            j++
        }
        ready.forEach { energy[it] = 0 }

        if(ready.size == energy.size)
            println(step)

        /*println()
        println(step)
        energy.forEachIndexed { index, i -> if(index % width == width-1) println(i) else print(i) }*/
    }
}