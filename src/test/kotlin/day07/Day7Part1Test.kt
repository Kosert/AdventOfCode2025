package day07

import MultipleCasesTest
import kotlin.test.Test

class Day7Part1Test : MultipleCasesTest<String, Int>() {

    override val testData: List<Pair<String, Int>> = listOf(
        day7TestInput to 123,
    )

    override fun execute(input: String) = day7Part1(input)

    @Test
    fun testPart1() = assertAllCases()
}

