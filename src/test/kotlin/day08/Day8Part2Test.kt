package day08

import MultipleCasesTest
import kotlin.test.Test

class Day8Part2Test : MultipleCasesTest<String, Int>() {

    override val testData: List<Pair<String, Int>> = listOf(
        day8TestInput to 123,
    )

    override fun execute(input: String) = day8Part2(input)

    @Test
    fun testPart2() = assertAllCases()
}

