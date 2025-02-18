package com.vladislaviliev.air.user.testing

import com.vladislaviliev.air.user.preferences.Dao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class InMemoryPreferencesDao(defaultLocationId: Int) : Dao {

    private val _currentLocation = MutableStateFlow(defaultLocationId)

    override val currentLocation: Flow<Int> = _currentLocation

    override suspend fun setCurrentLocation(id: Int) {
        _currentLocation.emit(id)
    }
}