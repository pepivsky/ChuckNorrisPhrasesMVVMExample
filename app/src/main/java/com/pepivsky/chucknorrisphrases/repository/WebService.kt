package com.pepivsky.chucknorrisphrases.repository

import com.google.gson.GsonBuilder
import com.pepivsky.chucknorrisphrases.data.model.PhraseModel
import com.pepivsky.chucknorrisphrases.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface WebService {

// https://api.chucknorris.io

    @GET("jokes/random")
    suspend fun getPhrase(): PhraseModel
}


/*
// object singleton
object RetrofitClient {
    val webService  by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}*/
