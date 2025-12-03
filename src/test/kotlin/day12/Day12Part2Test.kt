package day12

import MultipleCasesTest
import kotlin.test.Test

class Day12Part2Test : MultipleCasesTest<String, Int>() {

    override val testData: List<Pair<String, Int>> = listOf(
        day12TestInput to 123,
    )

    override fun execute(input: String) = day12Part2(input)

    @Test
    fun testPart2() = assertAllCases()
}

