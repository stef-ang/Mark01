package com.stef_ang.mark01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stef_ang.mark01.BuildConfig.TMDB_API3_KEY
import javax.inject.Singleton

class MainActivity : AppCompatActivity() {

    @Singleton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
