package utils

typealias List2D<T> = List<List<T>>

typealias Position = Pair<Int, Int>

val Position.x
    get() = first

val Position.y
    get() = second

val Pair<Double, Double>.x
    get() = first

val Pair<Double, Double>.y
    get() = second

fun Position.moved(dir: Dir) = Position(this.x + dir.x, this.y + dir.y)

fun Position.moved(dirs: Collection<Dir>): Position {
    val xMod = dirs.sumOf { it.x }
    val yMod = dirs.sumOf { it.y }
    return Position(x + xMod, y + yMod)
}

fun List<String>.toList2D(): List2D<Char> = map { it.map { it } }

fun <T> List2D<T>.getAt(x: Int, y: Int) = getOrNull(y)?.getOrNull(x)

fun <T> List2D<T>.getAt(position: Position) = getOrNull(position.y)?.getOrNull(position.x)

fun <T> List2D<T>.withPositions(): List2D<Pair<Position, T>> = mapIndexed { y, row -> row.mapIndexed { x, it -> Pair((x to y), it) } }

inline fun <T> List2D<T>.forEach2D(block: (T) -> Unit) {
    this.forEach{ row -> row.forEach { value -> block(value) } }
}

inline fun <T> List2D<T>.forEachIndexed2D(block: (position: Position, T) -> Unit) {
    this.withPositions().forEach2D { (position, value) -> block(position, value) }
}

fun <T, R> List2D<T>.map2D(block: (T) -> R): List2D<R> {
    return this.map { row -> row.map { value -> block(value) } }
}

fun <T, R> List2D<T>.map2DIndexed(block: (position: Position, T) -> R): List2D<R> {
    return this.withPositions().map2D { (position, value) -> block(position, value) }
}

inline fun <T> List2D<T>.findIndexOrNull(crossinline predicate: (T) -> Boolean): Position? {
    this.forEachIndexed2D { position, value ->
        if (predicate(value))
            return position
    }
    return null
}

inline fun <T> List2D<T>.findIndex(crossinline predicate: (T) -> Boolean): Position {
    return findIndexOrNull(predicate) ?: throw NoSuchElementException("No element found matching the predicate")
}

fun <T> List2D<T>.getColumn(xIndex: Int) = indices.map { y -> getAt(xIndex, y) }

val <T> List2D<T>.sizeX
    get() = first().size

val <T> List2D<T>.lastXIndex
    get() = first().lastIndex

val <T> List2D<T>.sizeY
    get() = size

val <T> List2D<T>.lastYIndex
    get() = lastIndex