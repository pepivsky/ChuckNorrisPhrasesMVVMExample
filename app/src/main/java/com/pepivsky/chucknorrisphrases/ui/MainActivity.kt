package com.pepivsky.chucknorrisphrases.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.pepivsky.chucknorrisphrases.R
import com.pepivsky.chucknorrisphrases.core.Resource
import com.pepivsky.chucknorrisphrases.data.model.PhraseModel
import com.pepivsky.chucknorrisphrases.presentation.PhraseViewModel
import com.pepivsky.chucknorrisphrases.ui.theme.MediumGray
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val TAG: String = this.javaClass.simpleName
    //lateinit var binding: ActivityMainBinding

    /*private val viewModel by viewModels<PhraseViewModel> { ViewModelFactory(PhraseRepositoryImpl(
        PhraseDataSource(RetrofitClient.webService)
    )) }*/
    private val viewModel:PhraseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*lifecycleScope.launchWhenCreated {
            viewModel.getPhrase()
        }*/

        setContent {
            MainScreen(phraseViewModel = viewModel)
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

   /* private fun initLoadAds() {
        val adRequest = AdRequest.Builder().build()
        binding.banner.loadAd(adRequest)
    }*/
}
//@Preview(showSystemUi = true)
@Composable
fun MainScreen(phraseViewModel: PhraseViewModel) {
    val uiState = phraseViewModel.homeUiState

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFFF7878))
        .clickable { phraseViewModel.getPhrase() }
    ) {

        when (uiState) {
            is Resource.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is Resource.Success -> {
                Text(modifier = Modifier.align(Alignment.TopCenter), text = "Tap Here")
                Text(modifier = Modifier.align(Alignment.Center), text = uiState.data.phrase)
            }

            is Resource.Failure -> {
                Icon(
                    modifier = Modifier.align(Alignment.Center).size(120.dp),
                    painter = painterResource(id = R.drawable.ic_no_internet),
                    tint = MediumGray,
                    contentDescription = null
                )
            }

        }
    }




}