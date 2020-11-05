package com.stef_ang.mark01.di

import android.app.Application
import com.stef_ang.mark01.fragment.HomeFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [HomeModule::class, HomeViewModelModule::class])
interface HomeComponent {
    fun inject(fragment: HomeFragment)

//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        fun application(application: Application): Builder
//
//        fun build(): HomeComponent
//    }

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): HomeComponent
    }
}