package day04

import utils.*

fun day4Part1(input: String): Int {
    val array = input.lines().map { it.toList() }
    var result = 0

    array.forEachIndexed2D { position, it ->
        if (it != '@') return@forEachIndexed2D
        val around = array.withPositions().getAllAround(position.x, position.y)
        if (around.count { it.second == '@' } < 4) {
            result++
        }
    }

    return result
}

fun day4Part2(input: String): Int {
    var array = input.lines().map { it.toList() }
    var removed = 0

    do {
        val removedSoFar = removed
        array = array.map2DIndexed { position, it ->
            if (it != '@') return@map2DIndexed it
            val around = array.getAllAround(position.x, position.y)
            if (around.count { it == '@' } < 4) {
                removed++
                return@map2DIndexed '.'
            } else it
        }
    } while (removed != removedSoFar)

    return removed
}

