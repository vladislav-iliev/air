package com.vladislaviliev.air.user.testing

import androidx.annotation.GuardedBy
import androidx.paging.PagingSource
import com.vladislaviliev.air.user.location.Dao
import com.vladislaviliev.air.user.location.LocationNotFoundException
import com.vladislaviliev.air.user.location.UserLocation
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.math.max

class InMemoryUserLocationDao : Dao {

    private val mutex = Mutex()

    @GuardedBy("mutex")
    private val locations = mutableMapOf<Int, UserLocation>()

    @GuardedBy("mutex")
    private var lastId = 0

    override suspend fun get(id: Int) =
        mutex.withLock { locations[id] } ?: throw LocationNotFoundException()

    override suspend fun upsert(userLocation: UserLocation) = mutex.withLock {
        val newId = if (userLocation.id == 0) lastId + 1 else userLocation.id
        lastId = max(lastId, newId)
        locations[newId] = userLocation
    }

    override suspend fun upsert(userLocations: Collection<UserLocation>) =
        userLocations.forEach { upsert(it) }

    override suspend fun exists(name: String) =
        mutex.withLock { locations.values }.any { it.name == name }

    override suspend fun getLastId() = mutex.withLock { lastId }

    override suspend fun delete(ids: Collection<Int>) =
        ids.forEach { mutex.withLock { locations.remove(it) } }

    override suspend fun deleteAllExcept(id: Int) = mutex.withLock { locations.keys }
        .filter { it != id }
        .forEach { mutex.withLock { locations.remove(it) } }

    override fun newPagingSource(excluding: Int): PagingSource<Int, UserLocation> {
        val locations = runBlocking { mutex.withLock { locations.values } }
            .filter { it.id != excluding }
            .sortedBy { it.name.first().uppercaseChar() }
        return StaticListPagingSource(locations)
    }
}