package com.stef_ang.mark01.di

import android.app.Application
import com.stef_ang.mark01.di.home.HomeComponent
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppSubComponent::class, DataModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun homeComponent(): HomeComponent.Factory
}