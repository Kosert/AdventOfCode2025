package utils

fun <T, R> cachedFun(function: ((T) -> R).(T) -> R): (T) -> R = object : (T) -> R {
    val map = mutableMapOf<T, R>()

    override fun invoke(p1: T): R = if (map.containsKey(p1))
        map.getValue(p1)
    else
        function(p1).also { map[p1] = it }
}

fun <T1, T2, R> cachedFun(function: ((T1, T2) -> R).(T1, T2) -> R): (T1, T2) -> R = object : (T1, T2) -> R {
    val map = mutableMapOf<Pair<T1, T2>, R>()

    override fun invoke(p1: T1, p2: T2): R {
        val key = Pair(p1, p2)
        return if (map.containsKey(key))
            map.getValue(key)
        else
            function(p1, p2).also { map[key] = it }
    }
}