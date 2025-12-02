package day02

import MultipleCasesTest
import kotlin.test.Test

class Day2Part1Test : MultipleCasesTest<String, Long>() {

    override val testData: List<Pair<String, Long>> = listOf(
        day2TestInput to 1227775554,
    )

    override fun execute(input: String) = day2Part1(input)

    @Test
    fun testPart1() = assertAllCases()
}