package day08

import MultipleCasesTest
import kotlin.test.Test

class Day8Part1Test : MultipleCasesTest<String, Int>() {

    override val testData: List<Pair<String, Int>> = listOf(
        day8TestInput to 123,
    )

    override fun execute(input: String) = day8Part1(input)

    @Test
    fun testPart1() = assertAllCases()
}

