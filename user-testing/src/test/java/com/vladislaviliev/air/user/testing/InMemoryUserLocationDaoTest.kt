package com.vladislaviliev.air.user.testing

import com.vladislaviliev.air.user.location.UserLocation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InMemoryUserLocationDaoTest {

    @Test
    fun upsert_is_synchronized() = runTest {
        val dao = InMemoryUserLocationDao()
        val n = 50_000

        repeat(n) {
            launch { dao.upsert(UserLocation(0, "", 0.0, 0.0)) }
        }

        advanceUntilIdle()
        Assert.assertEquals(n, dao.getLastId())
    }
}