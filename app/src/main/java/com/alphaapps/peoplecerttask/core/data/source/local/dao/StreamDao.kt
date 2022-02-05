package com.alphaapps.peoplecerttask.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alphaapps.peoplecerttask.core.data.source.local.entity.StreamSessionEntity
import io.reactivex.Single

/**
 * it provides access to [StreamSessions] through database
 *
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
@Dao
interface StreamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(streamSession: StreamSessionEntity): Long

    @Query("SELECT * FROM StreamSession")
    fun loadAll(): Single<List<StreamSessionEntity>>
}