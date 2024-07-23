package com.pepivsky.chucknorrisphrases.core

import com.pepivsky.chucknorrisphrases.data.model.PhraseModel
import java.lang.Exception

sealed interface Resource {

    object Loading: Resource

    data class Success(val data: PhraseModel): Resource

    object Failure: Resource
}