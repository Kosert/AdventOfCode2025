package day05

import MultipleCasesTest
import kotlin.test.Test

class Day5Part2Test : MultipleCasesTest<String, Int>() {

    override val testData: List<Pair<String, Int>> = listOf(
        day5TestInput to 123,
    )

    override fun execute(input: String) = day5Part2(input)

    @Test
    fun testPart2() = assertAllCases()
}