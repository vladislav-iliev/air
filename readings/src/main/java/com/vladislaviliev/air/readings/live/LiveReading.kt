package com.vladislaviliev.air.readings.live

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladislaviliev.air.readings.ReadingType

@Entity
data class LiveReading(

    @PrimaryKey
    val id: Int,

    val type: ReadingType,

    val latitude: Double,

    val longitude: Double,

    val measure: Double
)