package com.vladislaviliev.air.readings.history

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladislaviliev.air.readings.ReadingType

@Entity
data class HistoryReading(

    @PrimaryKey
    val daysBefore: Int,

    val type: ReadingType,

    val measure: Double,
)