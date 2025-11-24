package utils

enum class Dir(val x: Int, val y: Int, val symbol: Char) {
    UP(0, -1, '^'),
    RIGHT(1, 0, '>'),
    DOWN(0, 1, 'v'),
    LEFT(-1, 0, '<'),

    ;

    fun turned() = entries.getOrNull((this.ordinal + 1)) ?: UP

    companion object {
        fun fromSymbol(char: Char) = entries.first { it.symbol == char }
    }
}