package com.alphaapps.peoplecerttask.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alphaapps.peoplecerttask.core.data.source.local.dao.FrameDao
import com.alphaapps.peoplecerttask.core.data.source.local.dao.StreamDao
import com.alphaapps.peoplecerttask.core.data.source.local.entity.FrameSessionEntity
import com.alphaapps.peoplecerttask.core.data.source.local.entity.StreamSessionEntity

/**
 * To manage data items that can be accessed, updated and maintain relationships between them in the local database
 *
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
@Database(
    entities = [StreamSessionEntity::class, FrameSessionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract val streamDao: StreamDao
    abstract val frameDao: FrameDao

    companion object {
        const val DB_NAME = "PeopleCertTaskDatabase.db"
    }
}