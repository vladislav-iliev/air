package com.vladislaviliev.air.readings.calculations

import android.location.Location
import com.vladislaviliev.air.readings.ReadingType
import com.vladislaviliev.air.readings.live.LiveReading

object Maths {

    private fun distanceBetween(aLat: Double, aLng: Double, bLat: Double, bLng: Double) =
        Location("a").apply { latitude = aLat; longitude = aLng }
            .distanceTo(Location("b").apply { latitude = bLat; longitude = bLng })

    private fun LiveReading.distanceTo(lat: Double, lng: Double) =
        distanceBetween(latitude, longitude, lat, lng)

    private fun Iterable<LiveReading>.closestTo(lat: Double, lng: Double) =
        minWith { a, b -> (a.distanceTo(lat, lng) - b.distanceTo(lat, lng)).toInt() }

    private fun Iterable<LiveReading>.closestReadingTo(lat: Double, lng: Double) =
        closestTo(lat, lng).measure

    fun Collection<LiveReading>.average() = (sumOf { it.measure } / size).toInt().toDouble()

    fun Iterable<LiveReading>.closestReadingTo(lat: Double, lng: Double, type: ReadingType) =
        filter { it.type == type }.closestReadingTo(lat, lng)
}