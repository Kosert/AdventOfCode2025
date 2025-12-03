package day11

import MultipleCasesTest
import kotlin.test.Test

class Day11Part2Test : MultipleCasesTest<String, Int>() {

    override val testData: List<Pair<String, Int>> = listOf(
        day11TestInput to 123,
    )

    override fun execute(input: String) = day11Part2(input)

    @Test
    fun testPart2() = assertAllCases()
}

