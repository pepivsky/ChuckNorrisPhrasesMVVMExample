package com.pepivsky.chucknorrisphrases.core

import com.pepivsky.chucknorrisphrases.data.model.PhraseModel

sealed interface UIState {

    object Loading: UIState

    data class Success(val data: PhraseModel): UIState

    object Failure: UIState
}