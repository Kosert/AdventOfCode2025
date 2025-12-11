package day10

import MultipleCasesTest
import kotlin.test.Test

class Day10Part1Test : MultipleCasesTest<String, Long>() {

    override val testData: List<Pair<String, Long>> = listOf(
        day10TestInput to 7,
    )

    override fun execute(input: String) = day10Part1(input)

    @Test
    fun testPart1() = assertAllCases()
}

