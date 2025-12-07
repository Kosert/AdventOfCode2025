package day07

import utils.cachedFun

fun day7Part1(input: String): Int {
    val array = input.lines().map { it.toList() }

    var splitCount = 0
    val beamsX = mutableSetOf<Int>(array.first().indexOf('S'))
    array.drop(1).forEach { row ->
        row.forEachIndexed { x, it ->
            if (it == '^' && beamsX.contains(x)) {
                splitCount++
                beamsX.remove(x)
                beamsX.add(x - 1)
                beamsX.add(x + 1)
            }
        }
    }

    return splitCount
}


fun day7Part2(input: String): Long {
    val array = input.lines().map { it.toList() }

    val cachedSumUniverses: (Int, Int) -> Long = cachedFun { x: Int, y: Int ->
        return@cachedFun when {
            y == array.lastIndex -> 1
            array[y][x] == '^' -> this(x - 1, y + 1) + this(x + 1, y + 1)
            else -> this(x, y + 1)
        }
    }

    val startX = array.first().indexOf('S')
    return cachedSumUniverses(startX, 0)
}