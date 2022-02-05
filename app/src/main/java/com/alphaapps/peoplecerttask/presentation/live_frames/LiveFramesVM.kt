package com.alphaapps.peoplecerttask.presentation.live_frames

import androidx.lifecycle.ViewModel
import com.alphaapps.peoplecerttask.core.interactors.base.FramesSessionInteractor
import com.alphaapps.peoplecerttask.presentation.base.BaseUiModel
import com.alphaapps.peoplecerttask.presentation.base.Status
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
class LiveFramesVM @Inject constructor(
    private val frameSessionInteractor: FramesSessionInteractor,
) : ViewModel() {
    private val TAG = LiveFramesVM::class.java.simpleName
    private var disposable: Disposable? = null
    var framesUiModel = BaseUiModel<Boolean>()

    fun insertFrameSession(noOfFrames: Int) {
        framesUiModel.status.value = Status.LOADING.value
        frameSessionInteractor.insertFrameSession(noOfFrames)
        framesUiModel.status.value = Status.SUCCESS.value
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