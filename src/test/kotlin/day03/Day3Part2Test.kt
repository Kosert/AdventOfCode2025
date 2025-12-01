package day03

import MultipleCasesTest
import kotlin.test.Test

class Day3Part2Test : MultipleCasesTest<String, Int>() {

    override val testData: List<Pair<String, Int>> = listOf(
        day3TestInput to 123,
    )

    override fun execute(input: String) = day3Part2(input)

    @Test
    fun testPart2() = assertAllCases()
}