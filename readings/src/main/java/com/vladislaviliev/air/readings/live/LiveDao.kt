package com.vladislaviliev.air.readings.live

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface LiveDao {

    @Upsert
    suspend fun upsert(readings: Iterable<LiveReading>)

    @Query("SELECT * FROM LiveReading")
    fun getAll(): Flow<List<LiveReading>>

    @Query("DELETE FROM LiveReading")
    suspend fun deleteAll()
}