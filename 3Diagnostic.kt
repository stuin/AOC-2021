package Event4_AOC

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val reader = File(args[0])
    var common = arrayOf(0)
    var size = 0
    var count = 0

    reader.forEachLine {
        if(size == 0) {
            common = Array(it.length) { 0 }
            size = it.length
        }

        for(i in common.indices)
            common[i] += it[i].digitToInt()
        count++
    }

    val gamma = BitSet(size)
    val epsilon = BitSet(size)

    for(i in common.indices) {
        gamma[size-i-1] = common[i] > count / 2
        epsilon[size-i-1] = common[i] < count / 2
        print("${common[i]} ${gamma[size-i]} ")
    }

    var a = 0L
    var b = 0L

    for (i in 0 until gamma.length()) {
        a += if(gamma.get(i)) (1L shl i) else 0L
        b += if(epsilon.get(i)) (1L shl i) else 0L
    }

    println()
    println("$a * $b")
    println(a * b)
}