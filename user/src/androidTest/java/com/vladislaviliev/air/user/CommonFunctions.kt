package com.vladislaviliev.air.user

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.vladislaviliev.air.user.location.PrepopulateDatabase
import com.vladislaviliev.air.user.location.UserLocation

object CommonFunctions {

    fun getContext() = InstrumentationRegistry.getInstrumentation().targetContext

    val preloadedUserLocation = UserLocation(0, "TEST", 3.0, 3.0)

    fun provideLocationsDatabase(appContext: Context, preloadedUserLocation: UserLocation) =
        Room.inMemoryDatabaseBuilder(appContext, UserDatabase::class.java)
        .addCallback(PrepopulateDatabase(preloadedUserLocation))
        .build()
}