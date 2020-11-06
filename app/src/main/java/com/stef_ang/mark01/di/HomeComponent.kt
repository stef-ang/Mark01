package com.stef_ang.mark01.di

import com.stef_ang.mark01.fragment.HomeFragment
import dagger.Subcomponent

@Subcomponent(modules = [HomeModule::class, HomeViewModelModule::class])
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(fragment: HomeFragment)
}