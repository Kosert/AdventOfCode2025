package day01

fun day1Part1(input: String): Int {

    var pointer = 50
    val code = input.lineSequence()
        .map {
            println(pointer)
            println(it)
            val sign = when (it.first()) {
                'L' -> -1
                'R' -> 1
                else -> error("Invalid: $it")
            }
            val value = it.drop(1).toInt() * sign

            pointer = (pointer + value) % 100
            if (pointer < 0)
                pointer += 100

            pointer
        }

    return code.count { it == 0 }
}

fun day1Part2(input: String): Int {
    var result = 0
    var pointer = 50
    input.lineSequence()
        .forEach {
            println(pointer)
            println(it)
            val sign = when (it.first()) {
                'L' -> -1
                'R' -> 1
                else -> error("Invalid: $it")
            }
            val value = it.drop(1).toInt()
            for (i in 0 until value) {
                pointer += sign
                pointer %= 100
                if (pointer < 0) {
                    pointer += 100
                }
                if (pointer == 0) {
                    result++
                }
            }
        }

    return result
}