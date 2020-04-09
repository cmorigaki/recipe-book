package br.com.recipebook.recipecollection.di

import br.com.recipebook.recipecollection.data.remote.RecipeCollectionApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


internal object ServiceLocator {
    private val okHttpClient by lazy { OkHttpClient() }
    private val converterFactory by lazy { MoshiConverterFactory.create() }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://cm-recipe-book.s3-sa-east-1.amazonaws.com")
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    fun getApi() = retrofit.create(RecipeCollectionApi::class.java)
}