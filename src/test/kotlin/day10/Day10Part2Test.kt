package day10

import MultipleCasesTest
import kotlin.test.Test

class Day10Part2Test : MultipleCasesTest<String, Int>() {

    override val testData: List<Pair<String, Int>> = listOf(
        day10TestInput to 33
    )

    override fun execute(input: String) = day10Part2(input)

    @Test
    fun testPart2() = assertAllCases()
}
