package com.vladislaviliev.air.readings.downloader.metadata

import kotlinx.coroutines.flow.Flow

interface Dao {
    val data: Flow<Metadata>
    suspend fun store(metadata: Metadata)
    suspend fun clear()
}