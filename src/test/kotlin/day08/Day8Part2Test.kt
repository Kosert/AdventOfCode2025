package day08

import MultipleCasesTest
import kotlin.test.Test

class Day8Part2Test : MultipleCasesTest<String, Long>() {

    override val testData: List<Pair<String, Long>> = listOf(
        day8TestInput to 25272,
    )

    override fun execute(input: String) = day8Part2(input)

    @Test
    fun testPart2() = assertAllCases()
}

