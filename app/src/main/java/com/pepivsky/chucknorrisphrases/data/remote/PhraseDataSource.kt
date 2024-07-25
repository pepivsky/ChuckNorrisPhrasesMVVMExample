package com.pepivsky.chucknorrisphrases.data.remote

import com.pepivsky.chucknorrisphrases.data.model.PhraseModel
import com.pepivsky.chucknorrisphrases.repository.WebService

class PhraseDataSource(private val webService: WebService) {
    // metodos finales que obtendran los datos
    suspend fun getPhrase(): PhraseModel {
        return webService.getPhrase()
    }
}