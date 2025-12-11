package day10

import utils.println
import kotlin.time.measureTime

private data class Light(
    val target: Long,
    val switches: List<Long>,
)

fun day10Part1(input: String): Long {

    val lights = input.lineSequence()
        .map { line ->
            val target = line.drop(1)
                .substringBefore(']')
                .map { if (it == '#') "1" else "0" }
                .joinToString("")

            val switches = line.split("(")
                .drop(1)
                .map { switchString ->
                    val indices = switchString.substringBefore(')').split(",").map { it.toInt() }
                    List(target.length) { if (indices.contains(it)) "1" else "0" }
                        .joinToString("")
                        .toLong(2)
                }
            Light(target.toLong(2), switches)
        }

    return lights.sumOf { light ->
        var counter = 0L
        var states = setOf(0L)

        while (true) {
            counter++
            if (states.any { it xor light.target in light.switches }) {
                break
            }

            states = states.flatMapTo(mutableSetOf()) { state ->
                light.switches.map { state xor it }
            }
        }
        counter
    }
}

//not very fast, but it works
fun day10Part2(input: String): Int {
    val lights = input.lineSequence()
        .map { line ->
            val switches = line.split("(")
                .drop(1)
                .map { switchString ->
                    switchString.substringBefore(')').split(",").map { it.toInt() }
                }

            val joltages = line.substringAfter("{")
                .substringBefore("}")
                .split(",")
                .map { it.toInt() }
                .also { it.println() }
                .toList()

            switches to joltages
        }

    return lights.sumOf { (switches, joltages) ->
        var best = Int.MAX_VALUE

        val indicesByRarity = joltages.indices.sortedBy { index ->
            switches.flatten().count { it == index }
        }

        val sortedSwitches = switches.sortedWith(
            compareBy<List<Int>> { switch ->
                indicesByRarity.indexOfFirst { switch.contains(it) }
            }.thenByDescending { it.size }
        )

        fun solve(state: List<Int>, switches: List<List<Int>>, counter: Int) {
            val maxValue = state.max()
            if (counter + maxValue >= best)
                return

            if (switches.isEmpty()) {
                if (maxValue == 0) {
                    best = counter
                }
                return
            }

            val switch = switches.first()
            val max = switch.minOf { state[it] }

            var min = 0
            switch.forEach { index ->
                if (switches.count { it.contains(index) } == 1) {
                    if (min < state[index]) {
                        min = state[index]
                    }
                }
            }

            if (min > max) return

            for (switchPresses in min..max) {
                val newState = state.mapIndexed { index, it ->
                    if (index in switch) it - switchPresses else it
                }
                solve(newState, switches.drop(1), counter + switchPresses)
            }
        }

        measureTime {
            solve(joltages, sortedSwitches, 0)
        }.println()
        best.also { println("Best: $it") }
    }
}