package com.pepivsky.chucknorrisphrases.repository

import com.pepivsky.chucknorrisphrases.data.model.PhraseModel

interface PhraseRepository {

    suspend fun getPhrase(): PhraseModel

}