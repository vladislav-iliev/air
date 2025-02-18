package com.vladislaviliev.air.user.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.map

class PersistentDao(
    private val dataStore: DataStore<Preferences>,
    private val defaultLocationId: Int,
) : Dao {

    private val currentLocationIdKey = intPreferencesKey("CURRENT_LOCATION_ID")

    override val currentLocation =
        dataStore.data.map { it[currentLocationIdKey] ?: defaultLocationId }

    override suspend fun setCurrentLocation(id: Int) {
        dataStore.edit { it[currentLocationIdKey] = id }
    }
}