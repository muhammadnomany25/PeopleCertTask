package com.alphaapps.peoplecerttask.dagger.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alphaapps.peoplecerttask.dagger.factory.ViewModelProviderFactory
import com.alphaapps.peoplecerttask.presentation.live_frames.LiveFramesVM
import com.alphaapps.peoplecerttask.presentation.status.StatusDataVM
import com.alphaapps.peoplecerttask.presentation.streaming.LiveStreamingVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @Author: Muhammad Noamany
 * @Email: muhammadnoamany@gmail.com
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LiveFramesVM::class)
    internal abstract fun LiveFramesVM(LiveFramesVM: LiveFramesVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StatusDataVM::class)
    internal abstract fun StatusDataVM(StatusDataVM: StatusDataVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LiveStreamingVM::class)
    internal abstract fun LiveStreamingVM(LiveStreamingVM: LiveStreamingVM): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}