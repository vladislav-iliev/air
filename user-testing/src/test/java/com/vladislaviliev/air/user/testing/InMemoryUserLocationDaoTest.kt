package com.vladislaviliev.air.user.testing

import com.vladislaviliev.air.user.location.UserLocation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InMemoryUserLocationDaoTest {

    @Test
    fun get_last_id_is_correct() = runTest {
        val dao = InMemoryUserLocationDao()
        val n = 50_000

        repeat(n) {
            dao.upsert(UserLocation(0, "", 0.0, 0.0))
        }
        Assert.assertEquals(n, dao.getLastId())

        dao.deleteAllExcept(-1)
        dao.upsert(UserLocation(1_000_000, "", 0.0, 0.0))
        Assert.assertEquals(1_000_000, dao.getLastId())
    }
}