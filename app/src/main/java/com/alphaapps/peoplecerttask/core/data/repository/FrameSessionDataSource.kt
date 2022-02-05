package com.alphaapps.peoplecerttask.core.data.repository

import com.alphaapps.peoplecerttask.core.data.source.local.entity.FrameSessionEntity
import io.reactivex.Single

/**
 * To make an interaction between [TheFrameSessionRepository] & [GetFrameSerssionsUseCase]
 *
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
interface FrameSessionDataSource {
    fun getSessions(): Single<List<FrameSessionEntity>>
    fun insertSession(frameSessionEntity: FrameSessionEntity): Long
}