package com.vladislaviliev.air.readings.downloader

import com.vladislaviliev.air.readings.ReadingType
import com.vladislaviliev.air.readings.downloader.metadata.Metadata
import com.vladislaviliev.air.readings.history.HistoryReading
import com.vladislaviliev.air.readings.live.LiveReading
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class Downloader {
    private fun random() = (0..110).random().toDouble()
    private fun randomLow() = (0..30).random().toDouble()

    private fun newLiveReadings() = listOf(
        LiveReading(0, ReadingType.PM10, 54.973918, -1.624631, random()),
        LiveReading(1, ReadingType.PM10, 54.979667, -1.626219, random()),
        LiveReading(2, ReadingType.PM10, 54.982082, -1.616685, random()),
        LiveReading(3, ReadingType.PM10, 54.973908, -1.618864, random()),
        LiveReading(4, ReadingType.PM10, 54.974662, -1.635057, random()),
        LiveReading(5, ReadingType.PM10, 54.971311, -1.611821, random()),
        LiveReading(6, ReadingType.PM10, 54.967006, -1.613870, random()),
        LiveReading(7, ReadingType.PM10, 54.962771, -1.620061, random()),
        LiveReading(8, ReadingType.PM10, 54.973706, -1.614780, random()),
        LiveReading(9, ReadingType.PM10, 54.981417, -1.610347, random()),
        LiveReading(10, ReadingType.PM10, 54.976402, -1.608904, random()),
        LiveReading(11, ReadingType.PM10, 54.971259, -1.619555, random()),
        LiveReading(12, ReadingType.PM10, 54.970717, -1.622784, random()),
        LiveReading(13, ReadingType.PM10, 54.979261, -1.613316, random()),

        LiveReading(14, ReadingType.HUMID, 54.973918, -1.624631, randomLow()),
        LiveReading(15, ReadingType.HUMID, 54.979667, -1.626219, randomLow()),
        LiveReading(16, ReadingType.HUMID, 54.982082, -1.616685, randomLow()),
        LiveReading(17, ReadingType.HUMID, 54.973908, -1.618864, randomLow()),
        LiveReading(18, ReadingType.HUMID, 54.974662, -1.635057, randomLow()),
        LiveReading(19, ReadingType.HUMID, 54.971311, -1.611821, randomLow()),

        LiveReading(20, ReadingType.TEMP, 54.973918, -1.624631, randomLow()),
        LiveReading(21, ReadingType.TEMP, 54.979667, -1.626219, randomLow()),
        LiveReading(22, ReadingType.TEMP, 54.982082, -1.616685, randomLow()),
        LiveReading(23, ReadingType.TEMP, 54.973908, -1.618864, randomLow()),
        LiveReading(24, ReadingType.TEMP, 54.974662, -1.635057, randomLow()),
    )

    private fun newHistoryReadings() = listOf(
        HistoryReading(0, ReadingType.PM10, random()),
        HistoryReading(1, ReadingType.PM10, random()),
        HistoryReading(2, ReadingType.PM10, random()),
        HistoryReading(3, ReadingType.PM10, random()),
        HistoryReading(4, ReadingType.PM10, random()),
        HistoryReading(5, ReadingType.PM10, random()),
        HistoryReading(6, ReadingType.PM10, random()),
    )

    fun download() = DownloadResult(
        newLiveReadings(),
        newHistoryReadings(),
        Metadata("", Clock.System.now().toLocalDateTime(TimeZone.UTC).toString())
    )
}