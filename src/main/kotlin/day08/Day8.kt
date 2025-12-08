package day08

import kotlin.math.pow
import kotlin.math.sqrt

fun Triple<Int, Int, Int>.distanceTo(other: Triple<Int, Int, Int>): Double {
    return sqrt(
        (this.first - other.first).toDouble().pow(2) +
                (this.second - other.second).toDouble().pow(2) +
                (this.third - other.third).toDouble().pow(2)
    )
}

fun day8Part1(input: String): Long {
    val pairsToConnect = if (input.lines().size == 20) {
        10
    } else {
        1000
    }

    val boxes = input.lines().map { line ->
        val (x, y, z) = line.split(",").map { it.toInt() }
        Triple(x, y, z)
    }

    val circuits = boxes.map {
        setOf(it)
    }.toMutableList()

    val distances = buildMap {
        boxes.forEach { box ->
            val others = (boxes - box)
            others.forEach {
                val pair = setOf(box, it)
                if (!this.containsKey(pair)) {
                    val distance = pair.first().distanceTo(pair.last())
                    put(pair, distance)
                }
            }
        }
    }.toMutableMap()

    repeat(pairsToConnect) {
        val closest = distances.minBy { it.value }
        val firstCircuitIndex = circuits.indexOfFirst { it.contains(closest.key.first()) }
        val secondCircuitIndex = circuits.indexOfFirst { it.contains(closest.key.last()) }

        if (firstCircuitIndex != secondCircuitIndex) {
            circuits[firstCircuitIndex] = circuits[firstCircuitIndex] + circuits[secondCircuitIndex]
            circuits.removeAt(secondCircuitIndex)
            distances.remove(closest.key)
        } else {
            distances.remove(closest.key)
        }
    }

    return circuits.sortedByDescending { it.size }.take(3).fold(1L) { acc, triples ->
        acc * triples.size.toLong()
    }
}

fun day8Part2(input: String): Long {
    val boxes = input.lines().map { line ->
        val (x, y, z) = line.split(",").map { it.toInt() }
        Triple(x, y, z)
    }

    val circuits = boxes.map {
        setOf(it)
    }.toMutableList()

    val distances = buildMap {
        boxes.forEach { box ->
            val others = (boxes - box)
            others.forEach {
                val pair = setOf(box, it)
                if (!this.containsKey(pair)) {
                    val distance = pair.first().distanceTo(pair.last())
                    put(pair, distance)
                }
            }
        }
    }.toMutableMap()

    var lastConnection: Set<Triple<Int, Int, Int>>? = null
    while (circuits.size > 1) {
        val closest = distances.minBy { it.value }
        val firstCircuitIndex = circuits.indexOfFirst { it.contains(closest.key.first()) }
        val secondCircuitIndex = circuits.indexOfFirst { it.contains(closest.key.last()) }

        if (firstCircuitIndex != secondCircuitIndex) {
            circuits[firstCircuitIndex] = circuits[firstCircuitIndex] + circuits[secondCircuitIndex]
            circuits.removeAt(secondCircuitIndex)
            distances.remove(closest.key)
            lastConnection = closest.key
        } else {
            distances.remove(closest.key)
        }
    }

    return lastConnection!!.let { it.first().first.toLong() * it.last().first }
}



