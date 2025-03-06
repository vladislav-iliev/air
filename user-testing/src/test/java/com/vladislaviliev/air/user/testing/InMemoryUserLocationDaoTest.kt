package com.vladislaviliev.air.user.testing

import com.vladislaviliev.air.user.location.UserLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class InMemoryUserLocationDaoTest {

    @Test
    fun upsert_is_synchronized() = runBlocking(Dispatchers.Default) {
        val dao = InMemoryUserLocationDao()
        val n = 50_000

        coroutineScope {
            repeat(n) {
                launch { dao.upsert(UserLocation(0, "", 0.0, 0.0)) }
            }
        }

        Assert.assertEquals(n, dao.getLastId())
    }
}