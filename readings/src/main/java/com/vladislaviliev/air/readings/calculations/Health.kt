package com.vladislaviliev.air.readings.calculations

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import com.vladislaviliev.air.readings.R
import kotlin.collections.windowed

object Health {
    private val thresholds = listOf(0, 6, 17, 34, 51, 59, 67, 76, 84, 92, 101)

    @StringRes
    private val healthMessages = listOf(
        R.string.health_msg_enjoy,
        R.string.health_msg_enjoy,
        R.string.health_msg_enjoy,
        R.string.health_msg_enjoy,
        R.string.health_msg_possible_discomfort,
        R.string.health_msg_possible_discomfort,
        R.string.health_msg_possible_discomfort,
        R.string.health_msg_consider_reducing,
        R.string.health_msg_consider_reducing,
        R.string.health_msg_consider_reducing,
        R.string.health_msg_reduce_exertion,
    )

    private val backgroundColors = listOf(
        Color(0xFF47BBF0),
        Color(0xFF5BC78E),
        Color(0xFF57BB67),
        Color(0xFF44986A),
        Color(0xFFF39457),
        Color(0xFFF17C32),
        Color(0xFFEA642F),
        Color(0xFFED4639),
        Color(0xFFD63F3E),
        Color(0xFFCA3B3A),
        Color(0xFF8E3276),
    )

    private fun getThresholdIndex(pollution: Double): Int {
        var idx = thresholds.indexOfFirst { pollution <= it }
        if (idx < 0) idx = thresholds.lastIndex
        return idx
    }

    private fun getColor(index: Int) = backgroundColors[index]

    fun getColor(pollution: Double) = getColor(getThresholdIndex(pollution))

    @StringRes
    fun getHealthMessage(pollution: Double) = healthMessages[getThresholdIndex(pollution)]

    @Composable
    fun checkUnspecifiedBackgroundColor(color: Color) =
        color.takeOrElse(MaterialTheme.colorScheme::surface)

    @Composable
    fun checkUnspecifiedContentColor(color: Color) =
        color.takeOrElse(MaterialTheme.colorScheme::onSurface)

    /** ["0 - 6" -> Blue], ["6 - 17" -> Green], ..., ["101 - " -> Purple] */
    fun getThresholdsToColors() = thresholds
        .windowed(size = 2, partialWindows = true) {
            if (it.size > 1) "${it[0]} - ${it[1]}" else "${it[0]} - "
        }.mapIndexed { idx, str -> str to getColor(idx) }
}