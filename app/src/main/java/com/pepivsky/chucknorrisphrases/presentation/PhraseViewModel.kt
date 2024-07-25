package com.pepivsky.chucknorrisphrases.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.pepivsky.chucknorrisphrases.core.Resource
import com.pepivsky.chucknorrisphrases.data.model.PhraseModel
import com.pepivsky.chucknorrisphrases.repository.PhraseRepository
import com.pepivsky.chucknorrisphrases.repository.WebService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class PhraseViewModel @Inject constructor(private val phraseRepository: PhraseRepository): ViewModel() {

    var homeUiState: Resource by mutableStateOf(Resource.Loading)

    init {
        getPhrase()
    }


     fun getPhrase() {
        viewModelScope.launch {
            homeUiState = Resource.Loading
            homeUiState = try {
                val phrase = phraseRepository.getPhrase()
                Log.d("pruebilla", "getPhrase: ${phrase.phrase}")
                Resource.Success(phrase)
            } catch (e: Exception) {
                Resource.Failure
            }

        }
    }
}