package com.sgrom.asteroidtracker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sgrom.AsteroidTracker.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_host)
    }
}