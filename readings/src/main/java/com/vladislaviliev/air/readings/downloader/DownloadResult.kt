package com.vladislaviliev.air.readings.downloader

import com.vladislaviliev.air.readings.downloader.metadata.Metadata
import com.vladislaviliev.air.readings.history.HistoryReading
import com.vladislaviliev.air.readings.live.LiveReading

data class DownloadResult(
    val liveReadings: List<LiveReading>,
    val historyReadings: List<HistoryReading>,
    val metadata: Metadata,
)