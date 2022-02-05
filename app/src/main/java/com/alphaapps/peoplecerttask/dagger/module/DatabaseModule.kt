package com.alphaapps.peoplecerttask.dagger.module

import android.app.Application
import androidx.room.Room
import com.alphaapps.peoplecerttask.core.data.repository.FrameSessionRepository
import com.alphaapps.peoplecerttask.core.data.repository.StreamSessionRepository
import com.alphaapps.peoplecerttask.core.data.source.local.AppDatabase
import com.alphaapps.peoplecerttask.core.data.source.local.dao.FrameDao
import com.alphaapps.peoplecerttask.core.data.source.local.dao.StreamDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @Author: Muhammad Noamany
 * @Email: muhammadnoamany@gmail.com
 */
@Module
class DatabaseModule {
    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).allowMainThreadQueries().build()
    }

    @Provides
    internal fun provideStreamDao(appDatabase: AppDatabase): StreamDao {
        return appDatabase.streamDao
    }

    @Provides
    internal fun provideFrameDao(appDatabase: AppDatabase): FrameDao {
        return appDatabase.frameDao
    }


    @Singleton
    @Provides
    fun provideStreamSessionRepository(
        appDatabase: AppDatabase
    ): StreamSessionRepository {
        return StreamSessionRepository(appDatabase)
    }


    @Singleton
    @Provides
    fun provideFrameSessionRepository(
        appDatabase: AppDatabase
    ): FrameSessionRepository {
        return FrameSessionRepository(appDatabase)
    }

}