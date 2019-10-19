package com.stef_ang.mark01.model.di

import android.app.Application
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton
import dagger.BindsInstance



@Singleton
@Component()
interface AppComponent: AndroidInjector<DaggerApplication> {

    override fun inject(instance: DaggerApplication?)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}