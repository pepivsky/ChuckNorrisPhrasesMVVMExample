package com.pepivsky.chucknorrisphrases.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.pepivsky.chucknorrisphrases.R
import com.pepivsky.chucknorrisphrases.ads.AdvertView
import com.pepivsky.chucknorrisphrases.core.Resource
import com.pepivsky.chucknorrisphrases.data.model.PhraseModel
import com.pepivsky.chucknorrisphrases.presentation.PhraseViewModel
import com.pepivsky.chucknorrisphrases.ui.theme.MediumGray
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val TAG: String = this.javaClass.simpleName
    //lateinit var binding: ActivityMainBinding

    /*private val viewModel by viewModels<PhraseViewModel> { ViewModelFactory(PhraseRepositoryImpl(
        PhraseDataSource(RetrofitClient.webService)
    )) }*/
    private val viewModel: PhraseViewModel by viewModels()

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
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFFF7878)), horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Box(modifier = Modifier
            .weight(1F)
            .clickable { phraseViewModel.getPhrase() }
        ) {

            when (uiState) {
                is Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is Resource.Success -> {
                    Text(
                        modifier = Modifier.align(Alignment.TopCenter), text = "Tap Screen Anywhere",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.heavy, FontWeight.Bold)),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                    Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = uiState.data.phrase,
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.heavy, FontWeight.Bold)),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )

                        Text(
                            text = "-Chuck Norris Fact-",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.heavy, FontWeight.Bold)),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )



                    }
                    IconButton(modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(4.dp),
                        onClick = {
                            clipboardManager.setText(AnnotatedString(uiState.data.phrase))
                            Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_content_copy),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                }

                is Resource.Failure -> {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(120.dp),
                        painter = painterResource(id = R.drawable.ic_no_internet),
                        tint = MediumGray,
                        contentDescription = null
                    )
                }

            }
        }
        AdvertView(modifier = Modifier.height(50.dp))

    }


}