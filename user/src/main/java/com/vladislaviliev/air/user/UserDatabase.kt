package com.vladislaviliev.air.user

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vladislaviliev.air.user.location.Dao
import com.vladislaviliev.air.user.location.UserLocation

@Database(entities = [UserLocation::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userLocationDao(): Dao
}