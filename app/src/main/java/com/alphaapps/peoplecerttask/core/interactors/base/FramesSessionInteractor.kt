package com.alphaapps.peoplecerttask.core.interactors.base

import com.alphaapps.peoplecerttask.core.data.repository.FrameSessionRepository
import com.alphaapps.peoplecerttask.core.data.source.local.entity.FrameSessionEntity
import io.reactivex.Single
import javax.inject.Inject

/**
 * handles the response that returns data
 *
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
class FramesSessionInteractor @Inject constructor(private val frameSessionRepository: FrameSessionRepository) :
    BaseInteractor<List<FrameSessionEntity>>() {

    override fun buildUseCase(): Single<List<FrameSessionEntity>> {
        return frameSessionRepository.getSessions()
    }

    fun insertFrameSession(framesSent: Int) {
        frameSessionRepository.insertSession(
            FrameSessionEntity(
                sessionName = "test",
                framesSent = framesSent
            )
        )
    }
}