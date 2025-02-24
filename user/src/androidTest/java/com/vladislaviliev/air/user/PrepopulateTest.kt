package com.vladislaviliev.air.user

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vladislaviliev.air.user.location.PrepopulateDatabase
import com.vladislaviliev.air.user.location.UserLocation
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PrepopulateTest {
    @Test
    fun adds_location() {
        val ctx = CommonFunctions.getContext()
        val loc = CommonFunctions.preloadedUserLocation
        val db = Room.inMemoryDatabaseBuilder(ctx, UserDatabase::class.java)
            .addCallback(PrepopulateDatabase(loc))
            .build()

        val query = "SELECT * FROM " + UserLocation::class.simpleName
        val cursor = db.query(query, emptyArray())

        Assert.assertTrue(cursor.moveToFirst())
        Assert.assertEquals(loc.id, cursor.getInt(0))
        Assert.assertEquals(loc.name, cursor.getString(1))
        Assert.assertEquals(0, loc.latitude.compareTo(cursor.getDouble(2)))
        Assert.assertEquals(0, loc.longitude.compareTo(cursor.getDouble(3)))
        Assert.assertFalse(cursor.moveToNext())
        cursor.close()
        db.close()
    }
}