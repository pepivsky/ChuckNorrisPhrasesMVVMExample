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
    // LiveData<Resource<PhraseModel>>
    /*fun fetchPhrase(): LiveData<Resource<PhraseModel>> = liveData {
        // existen 3 estados en la app
        emit(Resource.Loading())
        try {
            emit(Resource.Success(phraseRepository.getPhrase()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }*/

    /*init {
        getPhrase()


    }*/

     fun getPhrase() {
        viewModelScope.launch {
            homeUiState = Resource.Loading
            homeUiState = try {
                val phrase = phraseRepository.getPhrase()
                Resource.Success(phrase)
            } catch (e: Exception) {
                Resource.Failure
            }
            /*val phrase = retrofit.create(WebService::class.java).getPhrase()
            Log.d("pruebilla", "getPhrase: ${phrase.phrase}")*/
        }
    }
}

/*

class ViewModelFactory(private val repository: PhraseRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PhraseRepository::class.java).newInstance(repository)
    }

}*/
