package com.stef_ang.mark01.di.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stef_ang.mark01.viewmodel.HomeNowPlayingVM
import com.stef_ang.mark01.viewmodel.HomePopularVM
import com.stef_ang.mark01.viewmodel.HomeUpcomingVM
import com.stef_ang.mark01.viewmodel.ViewModelFactory
import com.stef_ang.mark01.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(vmFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeNowPlayingVM::class)
    internal abstract fun bindHomeViewModel(vm: HomeNowPlayingVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomePopularVM::class)
    internal abstract fun bindHomePopularVM(vm: HomePopularVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeUpcomingVM::class)
    internal abstract fun bindHomeUpcomingVM(vm: HomeUpcomingVM): ViewModel
}