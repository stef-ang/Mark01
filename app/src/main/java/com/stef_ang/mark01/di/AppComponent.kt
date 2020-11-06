package com.stef_ang.mark01.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppSubComponent::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun homeComponent(): HomeComponent.Factory
}