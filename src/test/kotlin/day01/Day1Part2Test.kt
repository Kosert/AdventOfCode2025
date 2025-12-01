package day01

import MultipleCasesTest
import kotlin.test.Test

class Day1Part2Test : MultipleCasesTest<String, Int>() {

    override val testData: List<Pair<String, Int>> = listOf(
        day1TestInput to 6,
    )

    override fun execute(input: String) = day1Part2(input)

    @Test
    fun testPart2() = assertAllCases()
}