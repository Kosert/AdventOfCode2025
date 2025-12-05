package day05

import MultipleCasesTest
import kotlin.test.Test

class Day5Part2Test : MultipleCasesTest<String, Long>() {

    override val testData: List<Pair<String, Long>> = listOf(
        day5TestInput to 14,
    )

    override fun execute(input: String) = day5Part2(input)

    @Test
    fun testPart2() = assertAllCases()
}