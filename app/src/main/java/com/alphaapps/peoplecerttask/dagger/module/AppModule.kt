package com.alphaapps.peoplecerttask.dagger.module

import dagger.Module

/**
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
@Module(includes = [ActivityBuilderModule::class, DatabaseModule::class, SharedModule::class, ViewModelModule::class])
class AppModule {
}