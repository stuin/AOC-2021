package Event4_AOC1

import java.io.File

fun main(args: Array<String>) {
    val reader = File(args[0]).bufferedReader()
    val draws = reader.readLine().split(',').map { it.toInt() }
    reader.readLine()

    var bestTurn = 0

    var line: String? = reader.readLine()
    while(line != null) {
        val board: MutableList<List<Int>> = mutableListOf()
        (0 until 5).forEach {
            board.add(line!!.split(' ').filter { it != "" }.map { it.toInt() })
            line = reader.readLine()
        }

        var i = 0
        var found = false
        val selected = MutableList(board.size) { MutableList(board.size) { false } }

        //Check each turn
        while(i < draws.size && !found) {
            if(board.any { it.contains(draws[i]) }) {
                val y = board.indexOfFirst { it.contains(draws[i]) }
                val x = board[y].indexOfFirst { it == draws[i] }
                selected[y][x] = true

                //If last win
                if (selected[y].all { it } || (0 until board.size).all { selected[it][x] }) {
                    found = true
                    if(i > bestTurn) {
                        bestTurn = i

                        val score = board.mapIndexed { y2, list ->
                            list.filterIndexed { x2, _ ->
                                !selected[y2][x2]
                            }.sum()
                        }.sum() * draws[i]
                        println("Board found at turn $i with score $score")
                    }
                }
            }
            i++
        }
        line = reader.readLine()
    }
}