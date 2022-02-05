package com.alphaapps.peoplecerttask.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alphaapps.peoplecerttask.core.data.source.local.entity.FrameSessionEntity
import io.reactivex.Single

/**
 * it provides access to [FrameSessions] through database
 *
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
@Dao
interface FrameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(frameSessionEntity: FrameSessionEntity):Long

    @Query("SELECT * FROM FrameSession")
    fun loadAll(): Single<List<FrameSessionEntity>>

}