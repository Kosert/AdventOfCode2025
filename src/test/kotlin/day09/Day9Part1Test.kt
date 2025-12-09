package day09

import MultipleCasesTest
import kotlin.test.Test

class Day9Part1Test : MultipleCasesTest<String, Long>() {

    override val testData: List<Pair<String, Long>> = listOf(
        day9TestInput to 50,
    )

    override fun execute(input: String) = day9Part1(input)

    @Test
    fun testPart1() = assertAllCases()
}

