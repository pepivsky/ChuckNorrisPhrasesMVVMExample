package com.pepivsky.chucknorrisphrases.repository

import com.pepivsky.chucknorrisphrases.data.model.PhraseModel
import javax.inject.Inject

class PhraseRepository @Inject constructor(private val service: GetPhraseService) {

    suspend fun getPhrase(): PhraseModel {
        return service.getPhrase()
    }

}