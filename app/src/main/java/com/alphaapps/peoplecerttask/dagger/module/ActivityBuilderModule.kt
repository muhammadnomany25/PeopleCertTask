package com.alphaapps.peoplecerttask.dagger.module

import com.alphaapps.peoplecerttask.presentation.home.HomeActivity
import com.alphaapps.peoplecerttask.presentation.live_frames.LiveCameraFramesActivity
import com.alphaapps.peoplecerttask.presentation.status.StatusActivity
import com.alphaapps.peoplecerttask.presentation.streaming.LiveStreamingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module of Injecting app activities for Dagger to use in DI
 *
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun LiveStreamingActivity(): LiveStreamingActivity

    @ContributesAndroidInjector
    abstract fun LiveCameraFramesActivity(): LiveCameraFramesActivity

    @ContributesAndroidInjector
    abstract fun HomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun StatusActivity(): StatusActivity
}