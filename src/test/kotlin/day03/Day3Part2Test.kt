package day03

import MultipleCasesTest
import kotlin.test.Test

class Day3Part2Test : MultipleCasesTest<String, Long>() {

    override val testData: List<Pair<String, Long>> = listOf(
        day3TestInput to 3121910778619,
    )

    override fun execute(input: String) = day3Part2(input)

    @Test
    fun testPart2() = assertAllCases()
}