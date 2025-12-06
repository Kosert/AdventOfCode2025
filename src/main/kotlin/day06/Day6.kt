package day06

import utils.getColumn
import utils.lastXIndex

fun day6Part1(input: String): Long {
    val array = input.lines()
        .mapIndexed { index, it ->
            it.split(" ")
                .filter { it.isNotEmpty() }
                .toList()
        }

    return (0..array.lastXIndex).sumOf { x ->
        val column: List<String> = array.getColumn(x).filterNotNull()

        if (column.last() == "+") {
            column.dropLast(1).sumOf { it.toLong() }
        } else {
            column.dropLast(1).fold(1L) { acc, it -> acc * it.toLong() }
        }
    }
}

fun day6Part2(input: String): Long {
    val array = input.lines().map { it.toList() }

    var acc = 0L
    var operation: Char? = null
    return (0..array.lastXIndex).sumOf { x ->
        val column: List<Char> = array.getColumn(x).filterNotNull()
        if (column.all { it.isWhitespace() }) {
            return@sumOf acc
        }

        when (column.last()) {
            '+' -> {
                operation = column.last()
                acc = 0
            }
            '*' -> {
                operation = column.last()
                acc = 1
            }
            else -> Unit
        }

        val value = column.dropLast(1).filter { it.isDigit() }.joinToString("").toInt()
        when (operation) {
            '+' -> acc += value
            '*' -> acc *= value
            else -> error("Invalid operation")
        }

        if (x == array.lastXIndex) {
            return@sumOf acc
        }
        return@sumOf 0
    }
}

