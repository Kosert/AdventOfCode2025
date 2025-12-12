package day12

import utils.map2D
import utils.toList2D

data class Region(val width: Int, val length: Int, val required: List<Int>)

fun day12Part1(input: String): Int {
    val shapes = input.lineSequence()
        .chunked(5)
        .map { it.drop(1).dropLast(1).toList2D().map2D { it } }.take(6).toList()

    val regions = input.lines()
        .dropWhile { !it.contains('x') }
        .map {
            val sizeString = it.substringBefore(':')
            Region(
                sizeString.substringBefore('x').toInt(),
                sizeString.substringAfter('x').toInt(),
                it.split(" ").drop(1).map { it.toInt() }
            )
        }

    //todo try to implement proper solution
    return regions.map {
        val requiredTotalArea = it.required.withIndex().sumOf {
            shapes[it.index].sumOf { row -> row.count { it == '#' } } * it.value
        }
        val totalArea = it.width * it.length
        it to totalArea - requiredTotalArea
    }.filter { it.second >= 0 }
        .sortedByDescending { it.second }
        .size
}
