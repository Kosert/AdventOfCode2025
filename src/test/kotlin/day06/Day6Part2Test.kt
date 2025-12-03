package day06

import MultipleCasesTest
import kotlin.test.Test

class Day6Part2Test : MultipleCasesTest<String, Int>() {

    override val testData: List<Pair<String, Int>> = listOf(
        day6TestInput to 123,
    )

    override fun execute(input: String) = day6Part2(input)

    @Test
    fun testPart2() = assertAllCases()
}

