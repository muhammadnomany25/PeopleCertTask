package com.alphaapps.peoplecerttask.presentation.status

import android.util.Log
import androidx.lifecycle.ViewModel
import com.alphaapps.peoplecerttask.core.data.mapper.FramesSessionMapper
import com.alphaapps.peoplecerttask.core.data.mapper.StreamSessionMapper
import com.alphaapps.peoplecerttask.core.interactors.base.FramesSessionInteractor
import com.alphaapps.peoplecerttask.core.interactors.base.StreamSessionInteractor
import com.alphaapps.peoplecerttask.presentation.base.BaseUiModel
import com.alphaapps.peoplecerttask.presentation.base.Status
import com.nagwa.files.core.domain.FrameSessionModel
import com.nagwa.files.core.domain.StreamSessionModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
class StatusDataVM @Inject constructor(
    private val frameSessionInteractor: FramesSessionInteractor,
    private val streamSessionInteractor: StreamSessionInteractor,
) : ViewModel() {
    private val TAG = StatusDataVM::class.java.simpleName
    private var disposable: Disposable? = null
    var framesUiModel = BaseUiModel<FrameSessionModel>()
    var streamUiModel = BaseUiModel<StreamSessionModel>()
    private val streamManager = StreamSessionMapper()
    private val framesMapper = FramesSessionMapper()

    /**
     * load data based on sent param {isStream} to decide which to load
     */
    fun loadData(streams: Boolean) {
        if (streams) {
            loadStreams()
        } else {
            loadFrames()
        }
    }

    /**
     * Load stream data
     */
    private fun loadStreams() {
        streamUiModel.status.value = Status.LOADING.value
        streamSessionInteractor.execute(onSuccess = {
            streamUiModel.filesData.value = streamManager.mapFromEntityList(it)
            streamUiModel.status.value = Status.SUCCESS.value
        }, onError = {
            Log.e(TAG, "Error fetching stream sessions")
            streamUiModel.status.value = Status.SUCCESS.value
        })
    }

    /**
     * Load frames data
     */
    private fun loadFrames() {
        framesUiModel.status.value = Status.LOADING.value
        frameSessionInteractor.execute(onSuccess = {
            framesUiModel.filesData.value = framesMapper.mapFromEntityList(it)
            framesUiModel.status.value = Status.SUCCESS.value
        }, onError = {
            Log.e(TAG, "Error fetching frames sessions")
            framesUiModel.status.value = Status.ERROR.value
        })
    }

    /**
     * Clear disposables if exist
     */
    fun dispose() {
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }
}