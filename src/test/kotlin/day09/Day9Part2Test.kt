package day09

import MultipleCasesTest
import kotlin.test.Test

class Day9Part2Test : MultipleCasesTest<String, Int>() {

    override val testData: List<Pair<String, Int>> = listOf(
        day9TestInput to 123,
    )

    override fun execute(input: String) = day9Part2(input)

    @Test
    fun testPart2() = assertAllCases()
}

