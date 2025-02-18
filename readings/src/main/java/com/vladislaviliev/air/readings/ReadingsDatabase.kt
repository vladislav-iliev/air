package com.vladislaviliev.air.readings

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vladislaviliev.air.readings.history.HistoryDao
import com.vladislaviliev.air.readings.history.HistoryReading
import com.vladislaviliev.air.readings.live.LiveDao
import com.vladislaviliev.air.readings.live.LiveReading

@Database(entities = [LiveReading::class, HistoryReading::class], version = 1, exportSchema = false)
abstract class ReadingsDatabase : RoomDatabase() {

    abstract fun liveDao(): LiveDao
    abstract fun historyDao(): HistoryDao
}