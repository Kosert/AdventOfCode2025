package day01

import MultipleCasesTest
import kotlin.test.Test

class Day1Part1Test : MultipleCasesTest<String, Int>() {

    override val testData: List<Pair<String, Int>> = listOf(
        day1TestInput to 3,
    )

    override fun execute(input: String) = day1Part1(input)

    @Test
    fun testPart1() = assertAllCases()
}