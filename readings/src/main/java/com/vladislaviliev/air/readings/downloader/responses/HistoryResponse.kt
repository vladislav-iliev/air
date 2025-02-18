package com.vladislaviliev.air.readings.downloader.responses

import com.vladislaviliev.air.readings.history.HistoryReading
import com.vladislaviliev.air.readings.downloader.metadata.Metadata

data class HistoryResponse(
    val isLoading: Boolean,
    val readings: List<HistoryReading>,
    val metadata: Metadata,
)