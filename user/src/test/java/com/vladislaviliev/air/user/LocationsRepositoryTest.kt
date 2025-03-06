package com.vladislaviliev.air.user

import com.vladislaviliev.air.user.location.Dao
import com.vladislaviliev.air.user.location.LocationNotFoundException
import com.vladislaviliev.air.user.location.UserLocation
import com.vladislaviliev.air.user.location.UserLocationsRepository
import com.vladislaviliev.air.user.testing.InMemoryUserLocationDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LocationsRepositoryTest {

    private fun CoroutineScope.createRepo(protectedId: Int = 0): Pair<Dao, UserLocationsRepository> {
        val dao = InMemoryUserLocationDao()
        val dispatcher = coroutineContext[CoroutineDispatcher]!!
        val repo = UserLocationsRepository(this, dispatcher, dao, protectedId)
        return Pair(dao, repo)
    }

    @Test(expected = LocationNotFoundException::class)
    fun repo_gets() = runTest {
        val (dao, repo) = createRepo()
        dao.upsert(UserLocation(0, "test", 0.0, 0.0))
        advanceUntilIdle()
        val location = repo[0]
        advanceUntilIdle()
        Assert.assertEquals("test", location.name)
        repo[3]
        advanceUntilIdle()
    }

    @Test
    fun repo_adds() = runTest {
        val (dao, repo) = createRepo()
        repo.add("test", 0.0, 0.0)
        val exists = dao.exists("test")
        advanceUntilIdle()
        Assert.assertTrue(exists)
    }

    @Test
    fun repo_gets_last_id() = runTest {
        val (dao, repo) = createRepo()
        val ids = 4
        repeat(ids) { dao.upsert(UserLocation(it + 1, "", 0.0, 0.0)) }
        advanceUntilIdle()
        val lastId = repo.getLastId()
        advanceUntilIdle()
        Assert.assertEquals(ids, lastId)
    }
}

