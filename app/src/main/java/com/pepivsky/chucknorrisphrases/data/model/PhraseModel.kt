package com.pepivsky.chucknorrisphrases.data.model

import com.google.gson.annotations.SerializedName

data class PhraseModel(
    @SerializedName("value")
    val phrase: String
)