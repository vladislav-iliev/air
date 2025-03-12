package com.vladislaviliev.air.user.testing

import androidx.annotation.MainThread
import com.vladislaviliev.air.user.location.Dao
import com.vladislaviliev.air.user.location.LocationNotFoundException
import com.vladislaviliev.air.user.location.UserLocation
import kotlin.math.max

@MainThread
class InMemoryUserLocationDao : Dao {

    private val locations = mutableMapOf<Int, UserLocation>()

    private var lastId = 0

    override suspend fun get(id: Int) = locations[id] ?: throw LocationNotFoundException()

    override suspend fun upsert(userLocation: UserLocation) {
        val newId = if (userLocation.id == 0) lastId + 1 else userLocation.id
        lastId = max(lastId, newId)
        locations[newId] = userLocation
    }

    override suspend fun upsert(userLocations: Collection<UserLocation>) =
        userLocations.forEach { upsert(it) }

    override suspend fun exists(name: String) = locations.values.any { it.name == name }

    override suspend fun getLastId() = lastId

    override suspend fun delete(ids: Collection<Int>) = ids.forEach { locations.remove(it) }

    override suspend fun deleteAllExcept(id: Int) = locations.keys
        .filter { it != id }
        .forEach { locations.remove(it) }

    override fun newPagingSource(excluding: Int) = StaticListPagingSource(
        locations.values.filter { it.id != excluding }.sortedBy { it.name.first().uppercaseChar() }
    )
}