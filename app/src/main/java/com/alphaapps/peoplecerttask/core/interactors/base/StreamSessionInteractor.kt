package com.alphaapps.peoplecerttask.core.interactors.base

import com.alphaapps.peoplecerttask.core.data.repository.StreamSessionRepository
import com.alphaapps.peoplecerttask.core.data.source.local.entity.StreamSessionEntity
import io.reactivex.Single
import javax.inject.Inject

/**
 * handles the response that returns data
 *
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
class StreamSessionInteractor @Inject constructor(private val streamSessionRepository: StreamSessionRepository) :
    BaseInteractor<List<StreamSessionEntity>>() {

    override fun buildUseCase(): Single<List<StreamSessionEntity>> {
        return streamSessionRepository.getSessions()
    }

    fun insertStreamSession(bytesSent: Long) {
        streamSessionRepository.insertSession(
            StreamSessionEntity(
                sessionName = "test",
                sentBytes = bytesSent
            )
        )
    }
}