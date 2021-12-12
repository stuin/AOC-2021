package Event4_AOC

import java.io.File

fun main(args: Array<String>) {
    val reader = File(args[0])
    val paths = mutableMapOf<String, MutableList<String>>()

    reader.forEachLine {
        val from = it.split("-")[0]
        val to = it.split("-")[1]

        if(paths[from] != null)
            paths[from]?.add(to)
        else
            paths[from] = mutableListOf(to)

        if(paths[to] != null)
            paths[to]?.add(from)
        else
            paths[to] = mutableListOf(from)
    }

    fun findall(node: String, visited: List<String>, twice: Boolean): Int {
        if(node == "end") {
            //visited.forEach { print("$it ") }
            //println()
            return 1
        }
        if(paths[node] == null)
            return 0

        return paths[node]?.sumOf {
            if (it[0].isLowerCase() && !twice && visited.filter { value -> it == value  }.size == 1)
                findall(it, visited.plus(it), true)
            else if(it[0].isLowerCase() && !visited.contains(it))
                findall(it, visited.plus(it), twice)
            else if (it[0].isUpperCase())
                findall(it, visited.plus(it), twice)
            else
                0
        } ?: 0
    }

    println(findall("start", listOf("start", "start"), false))
}