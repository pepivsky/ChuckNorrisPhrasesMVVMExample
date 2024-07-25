package com.pepivsky.chucknorrisphrases.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepivsky.chucknorrisphrases.core.UIState
import com.pepivsky.chucknorrisphrases.repository.PhraseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhraseViewModel @Inject constructor(private val phraseRepository: PhraseRepository): ViewModel() {

    var homeUiState: UIState by mutableStateOf(UIState.Loading)

    init {
        getPhrase()
    }


     fun getPhrase() {
        viewModelScope.launch {
            homeUiState = UIState.Loading
            homeUiState = try {
                val phrase = phraseRepository.getPhrase()
                Log.d("pruebilla", "getPhrase: ${phrase.phrase}")
                UIState.Success(phrase)
            } catch (e: Exception) {
                UIState.Failure
            }

        }
    }
}