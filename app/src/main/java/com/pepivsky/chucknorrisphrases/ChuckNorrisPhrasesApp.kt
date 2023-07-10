package com.pepivsky.chucknorrisphrases

import android.app.Application
import com.google.android.gms.ads.MobileAds

class ChuckNorrisPhrasesApp: Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
    }
}