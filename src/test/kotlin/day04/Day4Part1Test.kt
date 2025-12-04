package day04

import MultipleCasesTest
import kotlin.test.Test

class Day4Part1Test : MultipleCasesTest<String, Int>() {

    override val testData: List<Pair<String, Int>> = listOf(
        day4TestInput to 13,
    )

    override fun execute(input: String) = day4Part1(input)

    @Test
    fun testPart1() = assertAllCases()
}