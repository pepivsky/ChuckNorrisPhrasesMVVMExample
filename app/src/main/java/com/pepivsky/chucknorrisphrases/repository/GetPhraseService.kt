package com.pepivsky.chucknorrisphrases.repository

import com.pepivsky.chucknorrisphrases.data.model.PhraseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class GetPhraseService @Inject constructor(private val retrofit: Retrofit) {
    suspend fun getPhrase(): PhraseModel {
        return withContext(Dispatchers.IO) {
            retrofit.create(WebService::class.java).getPhrase()
        }
    }
}