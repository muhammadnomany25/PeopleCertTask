package com.alphaapps.peoplecerttask.dagger.component

import android.app.Application
import android.content.Context
import com.alphaapps.peoplecerttask.app.ApplicationClass
import com.alphaapps.peoplecerttask.dagger.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class])
public interface AppComponent {
    fun inject(appClass: ApplicationClass)

    @Component.Builder
    public interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent

    }
}