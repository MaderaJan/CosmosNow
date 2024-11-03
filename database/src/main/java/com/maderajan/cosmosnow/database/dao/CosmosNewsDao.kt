package com.maderajan.cosmosnow.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maderajan.cosmosnow.database.entity.CosmosNewsEntity

@Dao
interface CosmosNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun persist(entity: CosmosNewsEntity)

    @Query("SELECT * FROM CosmosNewsEntity")
    suspend fun selectAll(): List<CosmosNewsEntity>

    @Query("DELETE FROM CosmosNewsEntity WHERE id = :id")
    suspend fun deleteById(id: Long)
}