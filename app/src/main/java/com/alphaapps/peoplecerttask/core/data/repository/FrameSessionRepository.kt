package com.alphaapps.peoplecerttask.core.data.repository

import com.alphaapps.peoplecerttask.core.data.source.local.AppDatabase
import com.alphaapps.peoplecerttask.core.data.source.local.entity.FrameSessionEntity
import io.reactivex.Single

/**
 * * This repository is responsible for
 * fetching data [FramesSessions] from local db
 *
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
class FrameSessionRepository(
    private val appDatabase: AppDatabase
) :
    FrameSessionDataSource {
    /**
     * Load All sessions from local db
     * */
    override fun getSessions(): Single<List<FrameSessionEntity>> {
        return appDatabase.frameDao.loadAll()
    }

    /**
     * Insert Item in the db
     */
    override fun insertSession(frameSessionEntity: FrameSessionEntity): Long {
        return appDatabase.frameDao.insert(frameSessionEntity)
    }


}