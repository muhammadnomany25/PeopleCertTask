package com.alphaapps.peoplecerttask.app

import android.content.res.Configuration
import androidx.multidex.MultiDexApplication
import com.alphaapps.peoplecerttask.dagger.component.AppComponent
import com.alphaapps.peoplecerttask.dagger.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


/**
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
class ApplicationClass : MultiDexApplication(), HasAndroidInjector {
    lateinit var appComponent: AppComponent

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    /**
     * Initializing of dagger
     */
    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .context(this.applicationContext)
            .application(this)
            .build()
        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any?> {
        return dispatchingAndroidInjector
    }

    @JvmName("getAppComponent1")
    fun getAppComponent(): AppComponent {
        return appComponent
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}