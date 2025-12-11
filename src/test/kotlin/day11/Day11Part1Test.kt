package day11

import MultipleCasesTest
import kotlin.test.Test

class Day11Part1Test : MultipleCasesTest<String, Long>() {

    override val testData: List<Pair<String, Long>> = listOf(
        day11TestInput to 5,
    )

    override fun execute(input: String) = day11Part1(input)

    @Test
    fun testPart1() = assertAllCases()
}

