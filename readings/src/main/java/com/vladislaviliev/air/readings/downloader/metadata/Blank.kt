package com.vladislaviliev.air.readings.downloader.metadata

internal val Blank get() = Metadata("", "")
fun Metadata.isBlank() = this == Blank