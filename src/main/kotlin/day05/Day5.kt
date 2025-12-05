package day05

import utils.overlaps
import kotlin.math.max
import kotlin.math.min

fun day5Part1(input: String): Int {
    val separator = input.lines().indexOfFirst { it.isEmpty() }

    val ranges = input.lineSequence().take(separator)
        .map { line ->
            line.split("-").map { it.toLong() }.let { (start, end) -> start..end }
        }.toList()

    val items = input.lineSequence().drop(separator + 1).map { it.toLong() }.toList()

    return items.count { item ->
        ranges.any { it.contains(item) }
    }
}

fun day5Part2(input: String): Long {
    val separator = input.lines().indexOfFirst { it.isEmpty() }

    val jointRanges = mutableListOf<LongRange>()
    input.lineSequence().take(separator)
        .map { line ->
            line.split("-").map { it.toLong() }.let { (start, end) -> start..end }
        }
        .forEach { range ->
            val overlapping = jointRanges.filter { it.overlaps(range) }
            if (overlapping.isEmpty()) {
                jointRanges.add(range)
                return@forEach
            }

            val start = min(overlapping.minOf { it.first }, range.first)
            val end = max(overlapping.maxOf { it.last }, range.last)
            jointRanges.removeAll(overlapping)
            jointRanges.add(start..end)
        }

    return jointRanges.sumOf { it.last - it.first + 1 }
}

