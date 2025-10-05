package com.bignerdranch.android.criminalintent

import android.app.Application
import android.content.Context

class CriminalIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this.applicationContext)
    }
}