package day11

import MultipleCasesTest
import kotlin.test.Test

class Day11Part2Test : MultipleCasesTest<String, Long>() {

    override val testData: List<Pair<String, Long>> = listOf(
        day11TestInput2 to 2,
    )

    override fun execute(input: String) = day11Part2(input)

    @Test
    fun testPart2() = assertAllCases()
}

