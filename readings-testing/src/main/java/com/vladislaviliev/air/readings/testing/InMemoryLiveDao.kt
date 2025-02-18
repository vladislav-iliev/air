package com.vladislaviliev.air.readings.testing

import com.vladislaviliev.air.readings.live.LiveReading
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class InMemoryLiveDao : com.vladislaviliev.air.readings.live.LiveDao {

    private val _state = MutableStateFlow(emptySet<LiveReading>())

    override suspend fun upsert(readings: Iterable<LiveReading>) {
        val newState = _state.value.toMutableSet().apply { addAll(readings) }
        _state.emit(newState)
    }

    override fun getAll(): Flow<List<LiveReading>> = _state.map { it.toList() }

    override suspend fun deleteAll() {
        _state.emit(emptySet())
    }
}