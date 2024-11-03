package com.maderajan.cosmosnow.data.model.comosnews

enum class CosmosNewsType : EnumWithKey {
    ARTICLE {
        override val key: String = "article"
    },
    BLOG{
        override val key: String = "blog"
    },
    REPORT{
        override val key: String = "report"
    },
}

interface EnumWithKey {
    val key: String
}

inline fun <reified T> enumFromKey(key: String): T where T : Enum<T>, T : EnumWithKey =
    enumValues<T>().firstOrNull {
        key == it.key
    } ?: throw IllegalStateException("Enum with key: $key not found")