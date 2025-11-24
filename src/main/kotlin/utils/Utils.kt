package utils

fun Any?.println() = println(this)

fun <T> List<T>.dropIndex(index: Int): List<T> = subList(0, index) + subList(index + 1, this.size)

infix fun ClosedRange<Double>.step(step: Double): Iterable<Double> {
    require(start.isFinite())
    require(endInclusive.isFinite())
    require(step > 0.0) { "Step must be positive, was: $step." }
    val sequence = generateSequence(start) { previous ->
        if (previous == Double.POSITIVE_INFINITY) return@generateSequence null
        val next = previous + step
        if (next > endInclusive) null else next
    }
    return sequence.asIterable()
}

//fixme change to List<Boolean>?
fun generateAllVariations(counter0: Int, counter1: Int, acc: List<String> = listOf("")): List<String> {
    if (counter0 == 0 && counter1 == 0) return acc
    return buildList {
        if (counter0 > 0)
            addAll(generateAllVariations(counter0 - 1, counter1, acc.map { it + "0" }))
        if (counter1 > 0)
            addAll(generateAllVariations(counter0, counter1 - 1, acc.map { it + "1" }))
    }
}

fun <T> permutations(list: Collection<T>): Set<List<T>> {
    if (list.isEmpty())
        return setOf(emptyList())

    return buildSet {
        list.forEach {
            permutations(list - it).forEach { item ->
                add(item + it)
            }
        }
    }
}
