package com.vladislaviliev.air.readings.downloader.responses

import com.vladislaviliev.air.readings.downloader.Downloader
import com.vladislaviliev.air.readings.history.HistoryDao
import com.vladislaviliev.air.readings.live.LiveDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import com.vladislaviliev.air.readings.downloader.metadata.Dao as MetadataDao

class ResponseRepository(
    private val scope: CoroutineScope,
    private val ioDispatcher: CoroutineDispatcher,
    private val downloader: Downloader,
    private val liveDao: LiveDao,
    private val historyDao: HistoryDao,
    private val metadataDao: MetadataDao,
) {
    /** Database and DataStore could emit very quickly after each other and
     * bombard the receivers with fast data. The determined purpose is to
     * collect both metadata and stored readings at once, if possible **/
    private val flowDebounceMillis = 200L

    private val isLoading = MutableStateFlow(false)

    @OptIn(FlowPreview::class)
    fun liveResponses() = combine(
        isLoading,
        liveDao.getAll(),
        metadataDao.data,
        ::LiveResponse
    ).debounce(flowDebounceMillis)

    @OptIn(FlowPreview::class)
    fun historyResponses() = combine(
        isLoading,
        historyDao.getAll(),
        metadataDao.data,
        ::HistoryResponse
    ).debounce(flowDebounceMillis)

    suspend fun refresh() = if (isLoading.value) Unit else scope.launch(ioDispatcher) {
        isLoading.emit(true)

        liveDao.deleteAll()
        historyDao.deleteAll()
        metadataDao.clear()

        val download = downloader.download()

        liveDao.upsert(download.liveReadings)
        historyDao.upsert(download.historyReadings)
        metadataDao.store(download.metadata)

        isLoading.emit(false)
    }.join()
}