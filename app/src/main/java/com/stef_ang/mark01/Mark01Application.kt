package com.stef_ang.mark01

import android.app.Application
import com.stef_ang.mark01.di.AppComponent
import com.stef_ang.mark01.di.DaggerAppComponent

open class Mark01Application: Application() {

    val appComponent: AppComponent by lazy {
        initComponent()
    }

    open fun initComponent(): AppComponent {
        return DaggerAppComponent.factory().create(this)
    }
}