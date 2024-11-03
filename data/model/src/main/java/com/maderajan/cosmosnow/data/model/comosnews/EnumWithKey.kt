package com.maderajan.cosmosnow.data.model.comosnews

interface EnumWithKey {
    val key: String
}


inline fun <reified T> enumFromKey(key: String): T where T : Enum<T>, T : EnumWithKey =
    enumValues<T>().firstOrNull {
        key == it.key
    } ?: throw IllegalStateException("Enum with key: $key not found")