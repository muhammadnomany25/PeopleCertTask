package com.alphaapps.peoplecerttask.core.data.repository

import com.alphaapps.peoplecerttask.core.data.source.local.entity.StreamSessionEntity
import io.reactivex.Single

/**
 * To make an interaction between [TheStreamSessionRepository] & [GetStreamSerssionsUseCase]
 *
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
interface StreamSessionDataSource {
    fun getSessions(): Single<List<StreamSessionEntity>>
    fun insertSession(streamSessionEntity: StreamSessionEntity): Long
}