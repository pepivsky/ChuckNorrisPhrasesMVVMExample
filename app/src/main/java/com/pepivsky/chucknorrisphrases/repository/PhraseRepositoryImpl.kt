package com.pepivsky.chucknorrisphrases.repository

import com.pepivsky.chucknorrisphrases.data.remote.PhraseDataSource
import com.pepivsky.chucknorrisphrases.data.model.PhraseModel

class PhraseRepositoryImpl(private val dataSource: PhraseDataSource):  PhraseRepository {

    override suspend fun getPhrase(): PhraseModel {
        return dataSource.getPhrase()
    }
}