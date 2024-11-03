package com.maderajan.cosmosnow.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maderajan.cosmosnow.database.entity.CosmosNewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CosmosNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun persist(entity: CosmosNewsEntity)

    @Query("SELECT * FROM CosmosNewsEntity")
    fun selectAll(): Flow<List<CosmosNewsEntity>>

    @Query("SELECT * FROM CosmosNewsEntity")
    fun selectAll2(): List<CosmosNewsEntity>

    @Query("DELETE FROM CosmosNewsEntity WHERE id = :id")
    suspend fun deleteById(id: Long)
}