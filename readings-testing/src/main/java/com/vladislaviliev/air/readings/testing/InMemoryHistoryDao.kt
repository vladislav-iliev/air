package com.vladislaviliev.air.readings.testing

import com.vladislaviliev.air.readings.history.HistoryDao
import com.vladislaviliev.air.readings.history.HistoryReading
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class InMemoryHistoryDao : HistoryDao {

    private val _state = MutableStateFlow(emptySet<HistoryReading>())

    override suspend fun upsert(readings: Iterable<HistoryReading>) {
        val newState = _state.value.toMutableSet().apply { addAll(readings) }
        _state.emit(newState)
    }

    override fun getAll(): Flow<List<HistoryReading>> = _state.map { it.toList() }

    override suspend fun deleteAll() {
        _state.emit(emptySet())
    }
}