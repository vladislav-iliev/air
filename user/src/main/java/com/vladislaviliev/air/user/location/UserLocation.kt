package com.vladislaviliev.air.user.location

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserLocation(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val latitude: Double,

    val longitude: Double,
)