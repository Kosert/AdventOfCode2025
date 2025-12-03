package day06

import MultipleCasesTest
import kotlin.test.Test

class Day6Part1Test : MultipleCasesTest<String, Int>() {

    override val testData: List<Pair<String, Int>> = listOf(
        day6TestInput to 123,
    )

    override fun execute(input: String) = day6Part1(input)

    @Test
    fun testPart1() = assertAllCases()
}

