package com.vladislaviliev.air.user.location

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class PrepopulateDatabase(private val loc: UserLocation) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        val values = ContentValues().apply {
            put(UserLocation::id.name, loc.id)
            put(UserLocation::name.name, loc.name)
            put(UserLocation::latitude.name, loc.latitude)
            put(UserLocation::longitude.name, loc.longitude)
        }
        db.insert(UserLocation::class.java.simpleName, SQLiteDatabase.CONFLICT_REPLACE, values)
    }
}