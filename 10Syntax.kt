package Event4_AOC

import java.io.File
import java.math.BigInteger
import java.util.*

fun main(args: Array<String>) {
    val reader = File(args[0])
    val scores = mutableListOf<BigInteger>()
    val mutiplier = BigInteger.valueOf(5)

    reader.forEachLine { line ->
        val open = Stack<Char>()
        var working = true
        var i = 0

        while(working && i < line.length) {
            when(line[i]) {
                '[', '(', '{', '<' -> open.push(line[i])
                ')' -> if(open.pop() != '(') working = false
                ']' -> if(open.pop() != '[') working = false
                '}' -> if(open.pop() != '{') working = false
                '>' -> if(open.pop() != '<') working = false
            }
            i++
        }

        if(working) {
            var score = BigInteger.ZERO
            while (open.isNotEmpty()) {
                when (open.pop()) {
                    '(' -> score = score * mutiplier + BigInteger.valueOf(1)
                    '[' -> score = score * mutiplier + BigInteger.valueOf(2)
                    '{' -> score = score * mutiplier + BigInteger.valueOf(3)
                    '<' -> score = score * mutiplier + BigInteger.valueOf(4)
                }
            }
            scores.add(score)

            println(score)
        }
    }

    println(scores.sorted()[scores.size / 2])
}