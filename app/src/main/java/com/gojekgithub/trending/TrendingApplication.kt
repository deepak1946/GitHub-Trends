package com.gojekgithub.trending

import android.app.Application
import com.gojekgithub.trending.di.DaggerTrendingApplicationComponent
import com.gojekgithub.trending.di.TrendingAppModule
import com.gojekgithub.trending.di.TrendingApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class TrendingApplication : Application(), HasAndroidInjector {

    @JvmField
    @Inject
    @Volatile
    var appDispatchingAndroidInjector: DispatchingAndroidInjector<Any>? = null

    lateinit var daggerAppComponent: TrendingApplicationComponent

    fun injectIfNecessary() {
        if (appDispatchingAndroidInjector == null) {
            synchronized(this) {
                if (appDispatchingAndroidInjector == null) {
                    initApplicationComponents()
                    checkNotNull(appDispatchingAndroidInjector) {
                        ("The AndroidInjector returned from applicationInjector() did not inject the "
                                + "DaggerApplication")
                    }
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        injectIfNecessary()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        injectIfNecessary()
        return appDispatchingAndroidInjector!!
    }

    open fun initApplicationComponents() {
        daggerAppComponent = DaggerTrendingApplicationComponent.builder()
            .trendingAppModule(TrendingAppModule(this@TrendingApplication)).build()
        daggerAppComponent.inject(this)
    }
}