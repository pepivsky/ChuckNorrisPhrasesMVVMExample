package com.pepivsky.chucknorrisphrases.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.ads.AdRequest
import com.pepivsky.chucknorrisphrases.R
import com.pepivsky.chucknorrisphrases.core.Resource
import com.pepivsky.chucknorrisphrases.data.remote.PhraseDataSource
import com.pepivsky.chucknorrisphrases.databinding.ActivityMainBinding
import com.pepivsky.chucknorrisphrases.presentation.PhraseViewModel
import com.pepivsky.chucknorrisphrases.repository.PhraseRepositoryImpl
import com.pepivsky.chucknorrisphrases.repository.RetrofitClient
import kotlin.math.log

class MainActivity : ComponentActivity() {

    private val TAG: String = this.javaClass.simpleName
    lateinit var binding: ActivityMainBinding

    /*private val viewModel by viewModels<PhraseViewModel> { ViewModelFactory(PhraseRepositoryImpl(
        PhraseDataSource(RetrofitClient.webService)
    )) }*/
    private val viewModel:PhraseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }

        /*binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadAds()

        binding.container.setOnClickListener {

            // observar el livedata
            viewModel.fetchPhrase().observe(this) { result ->
                when (result) {
                    is Resource.Loading -> {
                        Log.d("TAG", "onCreate: Loading...")
                        binding.spinner.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        Log.d("TAG", "onCreate: ${result.data.phrase}")
                        binding.groupNoInternet.visibility = View.GONE
                        binding.spinner.visibility = View.GONE
                        binding.groupWithInternet.visibility = View.VISIBLE
                        binding.tvPhrase.text = result.data.phrase
                    }

                    is Resource.Failure -> {
                        Log.d("TAG", "onCreate: ${result.exception}")
                        binding.groupWithInternet.visibility = View.GONE
                        binding.groupNoInternet.visibility = View.VISIBLE
                    }
                }
            }
        }*/
    }

    private fun initLoadAds() {
        val adRequest = AdRequest.Builder().build()
        binding.banner.loadAd(adRequest)
    }
}
@Preview(showSystemUi = true)
@Composable
fun MainScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFFF7878))) {
        Text(modifier = Modifier.align(Alignment.TopCenter),text ="Tap Here")
        Text(modifier = Modifier.align(Alignment.Center),text = "Phrase")

    }
}