package com.vladislaviliev.air.user.testing

import com.vladislaviliev.air.user.location.Dao
import com.vladislaviliev.air.user.location.UserLocation
import java.util.concurrent.atomic.AtomicInteger

class InMemoryUserLocationDao : Dao {

    private var lastId = AtomicInteger(0)
    private val locations = sortedMapOf<Int, UserLocation>(Int::compareTo)

    override suspend fun get(id: Int) = locations[id] ?: throw NoSuchElementException()

    override suspend fun upsert(userLocation: UserLocation) {
        val newId = if (userLocation.id == 0) lastId.incrementAndGet() else userLocation.id
        locations[newId] = userLocation
    }

    override suspend fun upsert(userLocations: Collection<UserLocation>) {
        userLocations.forEach { upsert(it) }
    }

    override suspend fun exists(name: String) = locations.values.any { it.name == name }

    override suspend fun getLastId() = lastId.get()

    override suspend fun delete(ids: Collection<Int>) {
        ids.forEach { locations.remove(it) }
    }

    override suspend fun deleteAllExcept(id: Int) {
        locations.keys.filter { it != id }.forEach { locations.remove(it) }
    }

    override fun newPagingSource(excluding: Int) = StaticListPagingSource(
        locations.values.filter { it.id != excluding }.sortedBy { it.name.first().uppercaseChar() }
    )
}