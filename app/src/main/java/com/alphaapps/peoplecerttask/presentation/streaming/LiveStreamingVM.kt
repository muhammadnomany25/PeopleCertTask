package com.alphaapps.peoplecerttask.presentation.streaming

import androidx.lifecycle.ViewModel
import com.alphaapps.peoplecerttask.core.interactors.base.StreamSessionInteractor
import com.alphaapps.peoplecerttask.presentation.base.BaseUiModel
import com.alphaapps.peoplecerttask.presentation.base.Status
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
class LiveStreamingVM @Inject constructor(
    private val streamSessionInteractor: StreamSessionInteractor,
) : ViewModel() {
    private val TAG = LiveStreamingVM::class.java.simpleName
    private var disposable: Disposable? = null
    var streamUiModel = BaseUiModel<Boolean>()

    fun insertFrameSession(sentBytes: Long) {
        streamUiModel.status.value = Status.LOADING.value
        streamSessionInteractor.insertStreamSession(sentBytes)
        streamUiModel.status.value = Status.SUCCESS.value
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