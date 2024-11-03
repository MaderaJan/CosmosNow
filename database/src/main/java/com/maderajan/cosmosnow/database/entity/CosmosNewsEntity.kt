package com.maderajan.cosmosnow.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CosmosNewsEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val type: String,
    val summary: String,
    val url: String,
    val newsSite: String,
    val imageUrl: String?,
    val publishedAt: String,
)