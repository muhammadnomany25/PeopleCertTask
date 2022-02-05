package com.alphaapps.peoplecerttask.core.data.repository

import com.alphaapps.peoplecerttask.core.data.source.local.AppDatabase
import com.alphaapps.peoplecerttask.core.data.source.local.entity.StreamSessionEntity
import io.reactivex.Single

/**
 * * This repository is responsible for
 * fetching data [StreamSessions] from local db
 *
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
class StreamSessionRepository(
    private val appDatabase: AppDatabase
) :
    StreamSessionDataSource {
    /**
     * Load All sessions from local db
     * */
    override fun getSessions(): Single<List<StreamSessionEntity>> {
        return appDatabase.streamDao.loadAll()
    }

    /**
     * Insert Item in the db
     */
    override fun insertSession(streamSessionEntity: StreamSessionEntity): Long {
        return appDatabase.streamDao.insert(streamSessionEntity)
    }

}