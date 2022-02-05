package com.alphaapps.peoplecerttask.dagger.module

import android.content.Context
import android.content.SharedPreferences
import com.alphaapps.peoplecerttask.shared_prefs.UserSaver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *  * Module of Shared Prefs for Dagger to use in DI
 *
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
@Module
class SharedModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(ctx: Context): SharedPreferences {
        return ctx.getSharedPreferences(ctx.applicationInfo.name, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesEditor(preferences: SharedPreferences): SharedPreferences.Editor {
        return preferences.edit()
    }

    @Provides
    @Singleton
    fun provideUserSaver(
        preferences: SharedPreferences,
        editor: SharedPreferences.Editor
    ): UserSaver {
        return UserSaver(preferences, editor)
    }
}