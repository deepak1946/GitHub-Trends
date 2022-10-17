package com.gojekgithub.trending.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.gojekgithub.trending.data.repositories.TrendingRepository
import com.gojekgithub.trending.ui.main.MainFragment
import com.gojekgithub.trending.ui.trendingrepo.MainViewModel
import com.gojekgithub.trending.ui.trendingrepo.TrendingViewModelFactory
import com.gojekgithub.trending.utils.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class TrendingViewModelModule {

    @Provides
    fun provideViewModelFactory(
        context: Context, trendingRepository: TrendingRepository, networkHelper: NetworkHelper,
    ): TrendingViewModelFactory {
        return TrendingViewModelFactory(
            context,
            trendingRepository,
            networkHelper
        )
    }

    @Provides
    fun provideMainViewModel(
        fragment: MainFragment,
        factory: TrendingViewModelFactory
    ): MainViewModel {
        return ViewModelProvider(fragment, factory).get(MainViewModel::class.java)
    }
}

