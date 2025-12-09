package day09

import utils.*
import kotlin.math.absoluteValue


fun day9Part1(input: String): Long {
    val reds = input.lineSequence()
        .map { it.split(",").map { it.toLong() } }
        .map { it.first() to it.last() }

    val areas = buildMap {
        reds.forEach { point ->
            val others = (reds - point)
            others.forEach {
                val pair = setOf(point, it)
                if (!this.containsKey(pair)) {
                    val width = (pair.first().x - pair.last().x).absoluteValue + 1L
                    val height = (pair.first().y - pair.last().y).absoluteValue + 1L
                    val area = width * height
                    put(pair, area)
                }
            }
        }
    }.toMutableMap()

    val biggest = areas.maxBy { it.value }
    return biggest.value
}

fun day9Part2(input: String): Long {
    val reds = input.lineSequence()
        .map { it.split(",").map { it.toInt() } }
        .map { it.first() to it.last() }
        .toList()

    val areas = buildMap {
        reds.forEach { point ->
            val others = (reds - point)
            others.forEach {
                val pair = setOf(point, it)
                if (!this.containsKey(pair)) {
                    val width = (pair.first().x - pair.last().x).absoluteValue + 1L
                    val height = (pair.first().y - pair.last().y).absoluteValue + 1L
                    val distance = width * height
                    put(pair, distance)
                }
            }
        }
    }.toMutableMap()

    fun testRange(
        first: Pair<Int, Int>,
        second: Pair<Int, Int>,
        step: Int? = null,
    ): Boolean {
        val xValues: Set<Int>
        val yValues: Set<Int>
        if (step != null) {
            xValues = listOf(first.x, second.x).sorted().let { (it.first()..it.last()).step(step).toSet() + it.last() }
            yValues = listOf(first.y, second.y).sorted().let { (it.first()..it.last()).step(step).toSet() + it.last() }
        } else {
            xValues = setOf(first.x, second.x)
            yValues = setOf(first.y, second.y)
        }
        return xValues.all { x ->
            yValues.all { y ->
                containsPoint(reds, x to y)
            }
        }
    }

    val biggest = areas.entries.sortedByDescending { it.value }
        .asSequence()
        .filter { (pair, _) ->
            testRange(pair.first(), pair.last())
        }
        .filter { (pair, _) ->
            testRange(pair.first(), pair.last(), 1000)
        }
        .filter { (pair, _) ->
            testRange(pair.first(), pair.last(), 100)
        }
        //more accuracy probably not needed
//        .filter { (pair, _) ->
//            testRange(pair.first(), pair.last(), 10)
//        }
//        .filter { (pair, _) ->
//            testRange(pair.first(), pair.last(), 1)
//        }
        .first()
    return biggest.value
}


/**
 * Stolen orthogonal polygon algorithm :)
 *
 * Determines if a point is contained within a 2D axis-aligned polygon using the ray casting algorithm.
 *
 * The algorithm works by casting a horizontal ray from the point to the right (towards positive infinity)
 * and counting how many times it crosses the polygon boundary. If the count is odd, the point is inside;
 * if even, it's outside.
 *
 * @param polygon List of vertices forming a closed polygon. Vertices should be in order (clockwise or counterclockwise).
 *                The polygon is assumed to be closed (last vertex connects back to first).
 * @param point The point to test, represented as a Pair<Int, Int> where first is x and second is y.
 * @return true if the point is inside the polygon, false otherwise.
 */
fun containsPoint(polygon: List<Pair<Int, Int>>, point: Pair<Int, Int>): Boolean {
    if (polygon.size < 3) return false

    val (px, py) = point

    // First check if point is on any edge (boundary points are considered inside)
    for (i in polygon.indices) {
        val v1 = polygon[i]
        val v2 = polygon[(i + 1) % polygon.size]

        val (x1, y1) = v1
        val (x2, y2) = v2

        // Check if point is on a vertical edge
        if (x1 == x2 && px == x1) {
            val minY = minOf(y1, y2)
            val maxY = maxOf(y1, y2)
            if (py in minY..maxY) {
                return true  // Point is on the edge
            }
        }

        // Check if point is on a horizontal edge
        if (y1 == y2 && py == y1) {
            val minX = minOf(x1, x2)
            val maxX = maxOf(x1, x2)
            if (px in minX..maxX) {
                return true  // Point is on the edge
            }
        }
    }

    // Ray casting algorithm for interior points
    var crossings = 0

    for (i in polygon.indices) {
        val v1 = polygon[i]
        val v2 = polygon[(i + 1) % polygon.size]

        val (x1, y1) = v1
        val (x2, y2) = v2

        // Check if this is a vertical edge (since polygon is axis-aligned)
        if (x1 == x2) {
            // Vertical edge: check if ray crosses it
            // Ray goes from point to the right (positive x direction)
            val minY = minOf(y1, y2)
            val maxY = maxOf(y1, y2)

            if (x1 > px && py >= minY && py < maxY) {
                crossings++
            }
        }
    }

    // Odd number of crossings means point is inside
    return crossings % 2 == 1
}