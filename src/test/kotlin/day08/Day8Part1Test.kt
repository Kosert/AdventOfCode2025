package day08

import MultipleCasesTest
import kotlin.test.Test

class Day8Part1Test : MultipleCasesTest<String, Long>() {

    override val testData: List<Pair<String, Long>> = listOf(
        day8TestInput to 40,
    )

    override fun execute(input: String) = day8Part1(input)

    @Test
    fun testPart1() = assertAllCases()
}

