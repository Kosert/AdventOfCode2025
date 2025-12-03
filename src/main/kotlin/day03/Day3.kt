package day03

import utils.allMaxBy

fun day3Part1(input: String): Int {
    return input.lineSequence()
        .map { it.map { it.digitToInt() }.withIndex() }
        .sumOf { row ->
            val maxes = row.allMaxBy { it.value }
            val max = maxes.first()
            if (maxes.size > 1) {
                (max.value.toString() + max.value.toString()).toInt()
            } else {
                (row.drop(max.index + 1).maxByOrNull { it.value }?.let {
                    (max.value.toString() + it.value.toString()).toInt()
                } ?: row.take(max.index).maxByOrNull { it.value }?.let {
                    (it.value.toString() + max.value.toString()).toInt()
                } ?: error("No max"))
            }
        }
}

fun day3Part2(input: String): Long {
    return input.lineSequence()
        .map { it.map { it.digitToInt() }.withIndex() }
        .sumOf { row ->
            var windowStart = -1
            List(12) {
                val max = row.drop(windowStart + 1)
                    .dropLast(11 - it)
                    .maxByOrNull { it.value }
                windowStart = max!!.index
                max.value
            }.joinToString("").toLong()
        }
}
