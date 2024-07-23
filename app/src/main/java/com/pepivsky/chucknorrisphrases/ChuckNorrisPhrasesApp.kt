package com.pepivsky.chucknorrisphrases

import android.app.Application
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ChuckNorrisPhrasesApp: Application() {
    /*override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
    }*/
}