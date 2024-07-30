package com.pepivsky.chucknorrisphrases.ui.screens

import android.widget.Toast
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pepivsky.chucknorrisphrases.R
import com.pepivsky.chucknorrisphrases.ads.AdvertView
import com.pepivsky.chucknorrisphrases.ads.adIsLoaded
import com.pepivsky.chucknorrisphrases.ads.showInterstitial
import com.pepivsky.chucknorrisphrases.core.UIState
import com.pepivsky.chucknorrisphrases.presentation.PhraseViewModel
import com.pepivsky.chucknorrisphrases.ui.theme.MediumGray

//@Preview(showSystemUi = true)
@Composable
fun MainScreen(phraseViewModel: PhraseViewModel) {
    val uiState = phraseViewModel.homeUiState
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    val randomNum = (1..10).random()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF7878)), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(1F)
                .fillMaxWidth()
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(),
                    onClick = {

                        if ((randomNum == 2 || randomNum == 7 || randomNum == 5) && adIsLoaded) {
                            showInterstitial(context = context) {
                                phraseViewModel.getPhrase()
                            }
                        } else {
                            phraseViewModel.getPhrase()
                        }
                    })
        ) {

            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp),
                text = "Tap Screen Anywhere",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.heavy, FontWeight.Bold)),
                color = Color.White,
                textAlign = TextAlign.Center,
            )

            when (uiState) {
                is UIState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is UIState.Success -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp),
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
                            Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT)
                                .show()
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_content_copy),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                }

                is UIState.Failure -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(120.dp)
                            //.fillMaxSize()
                            ,
                            painter = painterResource(id = R.drawable.ic_no_internet),
                            tint = MediumGray,
                            contentDescription = null
                        )

                        Text(
                            text = context.getString(R.string.tvCheckYourConnection),
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.heavy, FontWeight.Bold)),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
        AdvertView(modifier = Modifier.height(50.dp))
    }
}