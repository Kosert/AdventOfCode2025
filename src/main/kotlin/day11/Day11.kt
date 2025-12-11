package day11

fun day11Part1(input: String): Long {
    val map = input.lines()
        .associate { line ->
            val name = line.substringBefore(':')
            val others = line.substringAfter(':').trim().split(" ").toSet()
            name to others
        }

    val start = "you"
    val end = "out"

    val frontier = mutableListOf(start)
    val pathCounts = mutableMapOf(start to 1L)

    while (frontier.isNotEmpty()) {
        val current = frontier.removeFirst()
        if (current == end) {
            continue
        }

        val currentPathCount = pathCounts.getValue(current)
        map.getValue(current).forEach { node ->
            val pathCount = pathCounts[node]?: run {
                frontier.add(node)
                0
            }
            pathCounts[node] = currentPathCount + pathCount
        }
    }

    return pathCounts.getValue(end)
}

fun day11Part2(input: String): Long {
    val map = input.lines()
        .associate { line ->
            val name = line.substringBefore(':')
            val others = line.substringAfter(':').trim().split(" ").toSet()
            name to others
        }

    val start = "svr"
    val end = "out"

    data class PathCount(
        val paths: Long,
        val dacPaths: Long = 0,
        val fftPaths: Long = 0,
        val dacFftPaths: Long = 0
    )

    val frontier = mutableListOf(start)
    val pathCounts = mutableMapOf(start to PathCount(1, 0, 0, 0))

    while (frontier.isNotEmpty()) {
        val current = frontier.removeFirst()
        if (current == end) {
            continue
        }

        val currentPathCount = pathCounts.remove(current) ?: continue
        map.getValue(current).forEach { node ->
            val pathCount = pathCounts[node] ?: run {
                frontier.add(node)
                PathCount(0)
            }

            val newPathCount = when (node) {
                "dac" -> PathCount(
                    paths = pathCount.paths + currentPathCount.paths,
                    dacPaths = pathCount.dacPaths + currentPathCount.dacPaths + currentPathCount.paths,
                    fftPaths = pathCount.fftPaths + currentPathCount.fftPaths,
                    dacFftPaths = pathCount.dacFftPaths + currentPathCount.dacFftPaths + currentPathCount.fftPaths,
                )
                "fft" -> PathCount(
                    paths = pathCount.paths + currentPathCount.paths,
                    dacPaths = pathCount.dacPaths + currentPathCount.dacPaths,
                    fftPaths = pathCount.fftPaths + currentPathCount.fftPaths + currentPathCount.paths,
                    dacFftPaths = pathCount.dacFftPaths + currentPathCount.dacFftPaths + currentPathCount.dacPaths,
                )
                else -> PathCount(
                    paths = pathCount.paths + currentPathCount.paths,
                    dacPaths = pathCount.dacPaths + currentPathCount.dacPaths,
                    fftPaths = pathCount.fftPaths + currentPathCount.fftPaths,
                    dacFftPaths = pathCount.dacFftPaths + currentPathCount.dacFftPaths,
                )
            }

            pathCounts[node] = newPathCount
        }
    }

    return pathCounts.getValue(end).dacFftPaths
}