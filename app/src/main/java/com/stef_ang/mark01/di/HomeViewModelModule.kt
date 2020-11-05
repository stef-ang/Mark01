package com.stef_ang.mark01.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stef_ang.mark01.viewmodel.HomeViewModel
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
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(vm: HomeViewModel): ViewModel
}