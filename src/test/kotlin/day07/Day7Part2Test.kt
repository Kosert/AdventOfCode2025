package day07

import MultipleCasesTest
import kotlin.test.Test

class Day7Part2Test : MultipleCasesTest<String, Long>() {

    override val testData: List<Pair<String, Long>> = listOf(
        day7TestInput to 40,
    )

    override fun execute(input: String) = day7Part2(input)

    @Test
    fun testPart2() = assertAllCases()
}

