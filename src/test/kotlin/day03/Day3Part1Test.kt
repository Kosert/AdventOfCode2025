package day03

import MultipleCasesTest
import kotlin.test.Test

class Day3Part1Test : MultipleCasesTest<String, Int>() {

    override val testData: List<Pair<String, Int>> = listOf(
        day3TestInput to 123,
    )

    override fun execute(input: String) = day3Part1(input)

    @Test
    fun testPart1() = assertAllCases()
}