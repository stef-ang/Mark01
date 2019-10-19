package com.stef_ang.mark01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import javax.inject.Singleton

class MainActivity : AppCompatActivity() {

    @Singleton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
