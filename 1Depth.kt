package Event4_AOC

import java.io.File

fun main(args: Array<String>) {
    val reader = File(args[0])
    var last = 0

    var A = 0
    var B = 0
    var C = 0
    var count = -1

    reader.forEachLine {
        val depth = it.toInt()

        A = B
        B = C
        C = depth

        if(A > 0) {
            if(A + B + C > last)
                count++
            last = A + B + C
        }
    }

    println(count)
}