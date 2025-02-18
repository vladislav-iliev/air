package com.vladislaviliev.air.readings.downloader.metadata

val Metadata.Companion.Blank get() = Metadata("", "")
fun Metadata.isBlank() = this == Metadata.Blank