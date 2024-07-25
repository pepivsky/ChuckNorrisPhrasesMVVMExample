package com.pepivsky.chucknorrisphrases.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.pepivsky.chucknorrisphrases.ads.loadInterstitial
import com.pepivsky.chucknorrisphrases.presentation.PhraseViewModel
import com.pepivsky.chucknorrisphrases.ui.screens.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val TAG: String = this.javaClass.simpleName

    private val viewModel: PhraseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadInterstitial(this)

        setContent {
            MainScreen(phraseViewModel = viewModel)
        }
    }
}