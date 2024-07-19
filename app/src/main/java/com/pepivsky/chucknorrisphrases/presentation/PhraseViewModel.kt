package com.pepivsky.chucknorrisphrases.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.pepivsky.chucknorrisphrases.core.Resource
import com.pepivsky.chucknorrisphrases.repository.PhraseRepository
import kotlinx.coroutines.Dispatchers

class PhraseViewModel(private val repository: PhraseRepository): ViewModel() {

    // LiveData<Resource<PhraseModel>>
    fun fetchPhrase() = liveData(Dispatchers.IO) {
        // existen 3 estados en la app
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getPhrase()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

/*

class ViewModelFactory(private val repository: PhraseRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PhraseRepository::class.java).newInstance(repository)
    }

}*/
