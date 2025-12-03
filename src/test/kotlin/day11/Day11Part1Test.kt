package day11

import MultipleCasesTest
import kotlin.test.Test

class Day11Part1Test : MultipleCasesTest<String, Int>() {

    override val testData: List<Pair<String, Int>> = listOf(
        day11TestInput to 123,
    )

    override fun execute(input: String) = day11Part1(input)

    @Test
    fun testPart1() = assertAllCases()
}

