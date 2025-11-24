package utils

interface Node

data class Edge(val node1: Node, val node2: Node, val distance: Int)

/**
 * See https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 */
fun findShortestPath(edges: List<Edge>, source: Node, target: Node): ShortestPathResult {
    val dist = mutableMapOf<Node, Int>()
    val prev = mutableMapOf<Node, List<Node>>()
    val q = findDistinctNodes(edges)

    q.forEach { v ->
        dist[v] = Integer.MAX_VALUE
        prev[v] = listOf()
    }
    dist[source] = 0

    while (q.isNotEmpty()) {
        val u = q.minBy { dist[it] ?: 0 }
        q.remove(u)

        if (u == target) {
            break
        }
        edges
            .filter { it.node1 == u }
            .forEach { edge ->
                val v = edge.node2
                val alt = (dist[u] ?: 0) + edge.distance
                if (alt < (dist[v] ?: 0)) {
                    dist[v] = alt
                    prev[v] = listOf(u)
                }
                else if (alt == (dist[v] ?: 0)) {
                    dist[v] = alt
                    prev[v] = prev[v]!! + u
                }
            }
    }

    return ShortestPathResult(prev, dist, source, target)
}

private fun findDistinctNodes(edges: List<Edge>): MutableSet<Node> {
    val nodes = mutableSetOf<Node>()
    edges.forEach {
        nodes.add(it.node1)
        nodes.add(it.node2)
    }
    return nodes
}

class ShortestPathResult(val prev: Map<Node, List<Node>>, val dist: Map<Node, Int>, val source: Node, val target: Node) {

    fun shortestPath(from: Node = source, to: Node = target, list: List<Node> = emptyList()): List<Node> {
        val last = prev[to]?.firstOrNull() ?: return if (from == to) {
            list + to
        } else {
            emptyList()
        }
        return shortestPath(from, last, list) + to
    }

    fun distance(): Int = shortestDistance() ?: throw NullPointerException()

    fun shortestDistance(): Int? {
        val shortest = dist[target]
        if (shortest == Integer.MAX_VALUE) {
            return null
        }
        return shortest
    }
}