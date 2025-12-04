package day04

import MultipleCasesTest
import kotlin.test.Test

class Day4Part2Test : MultipleCasesTest<String, Int>() {

    override val testData: List<Pair<String, Int>> = listOf(
        day4TestInput to 43,
    )

    override fun execute(input: String) = day4Part2(input)

    @Test
    fun testPart2() = assertAllCases()
}