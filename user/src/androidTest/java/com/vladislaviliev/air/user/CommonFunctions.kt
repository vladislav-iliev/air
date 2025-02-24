package com.vladislaviliev.air.user

import androidx.test.platform.app.InstrumentationRegistry
import com.vladislaviliev.air.user.location.UserLocation

object CommonFunctions {

    fun getContext() = InstrumentationRegistry.getInstrumentation().targetContext

    val preloadedUserLocation = UserLocation(0, "TEST", 3.0, 3.0)
}