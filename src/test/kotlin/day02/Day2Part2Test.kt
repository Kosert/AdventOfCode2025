package day02

import MultipleCasesTest
import kotlin.test.Test

class Day2Part2Test : MultipleCasesTest<String, Long>() {

    override val testData: List<Pair<String, Long>> = listOf(
        day2TestInput to 4174379265,
    )

    override fun execute(input: String) = day2Part2(input)

    @Test
    fun testPart2() = assertAllCases()
}