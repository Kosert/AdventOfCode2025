package day06

import MultipleCasesTest
import kotlin.test.Test

class Day6Part1Test : MultipleCasesTest<String, Long>() {

    override val testData: List<Pair<String, Long>> = listOf(
        day6TestInput to 4277556,
    )

    override fun execute(input: String) = day6Part1(input)

    @Test
    fun testPart1() = assertAllCases()
}

