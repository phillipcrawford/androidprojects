package com.example.criminaintent

import android.app.Application

class CriminaIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}