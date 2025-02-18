package com.vladislaviliev.air.readings.downloader.responses

import com.vladislaviliev.air.readings.live.LiveReading
import com.vladislaviliev.air.readings.downloader.metadata.Metadata

data class LiveResponse(
    val isLoading: Boolean, val readings: Collection<LiveReading>, val metadata: Metadata,
)
