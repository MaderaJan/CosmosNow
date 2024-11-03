package com.maderajan.cosmosnow.data.model.comosnews

import com.maderajan.cosmosnow.data.model.R

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

fun CosmosNewsType.getPresentableNameRes(): Int =
    when (this) {
        CosmosNewsType.ARTICLE -> R.string.news_type_article
        CosmosNewsType.BLOG -> R.string.news_type_blog
        CosmosNewsType.REPORT -> R.string.news_type_report
    }