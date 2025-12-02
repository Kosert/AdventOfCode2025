package day02

fun day2Part1(input: String): Long {

    return input.splitToSequence(",")
        .map {
            val (start, end) = it.split("-").map { it.toLong() }
            start..end
        }
        .sumOf { range ->
            range.sumOf { id ->
                val asString = id.toString()
                repeat(asString.length / 2) {
                    val lengthToTest = it + 1
                    val prefix = asString.take(lengthToTest)
                    val chunked = asString.chunked(lengthToTest)
                    if (chunked.size == 2 && chunked.all { it == prefix }) {
                        println("Invalid $id")
                        return@sumOf id
                    }
                }
                return@sumOf 0
            }
        }
}

fun day2Part2(input: String): Long {

    return input.splitToSequence(",")
        .map {
            val (start, end) = it.split("-").map { it.toLong() }
            start..end
        }
        .sumOf { range ->
            range.sumOf { id ->
                val asString = id.toString()
                repeat(asString.length / 2) {
                    val lengthToTest = it + 1
                    val prefix = asString.take(lengthToTest)
                    val chunked = asString.chunked(lengthToTest)
                    if (chunked.all { it == prefix }) {
                        println("Invalid $id")
                        return@sumOf id
                    }
                }
                return@sumOf 0
            }
        }
}

