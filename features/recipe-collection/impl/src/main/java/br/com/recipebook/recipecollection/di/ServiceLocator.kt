package br.com.recipebook.recipecollection.di

import br.com.recipebook.recipecollection.data.RecipeCollectionDataSourceLocal
import br.com.recipebook.recipecollection.data.RecipeCollectionDataSourceRemote
import br.com.recipebook.recipecollection.data.RecipeCollectionRepositoryImpl
import br.com.recipebook.recipecollection.data.local.RecipeCollectionDataSourceLocalImpl
import br.com.recipebook.recipecollection.data.remote.RecipeCollectionApi
import br.com.recipebook.recipecollection.data.remote.RecipeCollectionDataSourceRemoteImpl
import br.com.recipebook.recipecollection.domain.RecipeCollectionRepository
import br.com.recipebook.recipecollection.domain.usecase.GetRecipeCollection
import br.com.recipebook.recipecollection.domain.usecase.GetRecipeCollectionUseCase
import br.com.recipebook.utilityandroid.network.BigDecimalAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


internal object ServiceLocator {
    private val okHttpClient by lazy { OkHttpClient() }
    private val converterFactory by lazy {
        MoshiConverterFactory.create(
            Moshi.Builder()
                .add(BigDecimalAdapter)
                .build()
        )
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://cm-recipe-book.s3-sa-east-1.amazonaws.com")
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    fun getApi() = retrofit.create(RecipeCollectionApi::class.java)

    fun getRecipeCollectionRepository() : RecipeCollectionRepository {
        return RecipeCollectionRepositoryImpl(
            dataSourceLocal = getDataSourceLocal(),
            dataSourceRemote = getDataSourceRemote()
        )
    }

    fun getDataSourceLocal() : RecipeCollectionDataSourceLocal {
        return RecipeCollectionDataSourceLocalImpl()
    }

    fun getDataSourceRemote(): RecipeCollectionDataSourceRemote {
        return RecipeCollectionDataSourceRemoteImpl(
            api = getApi()
        )
    }

    fun getRecipeCollection(): GetRecipeCollectionUseCase {
        return GetRecipeCollection(getRecipeCollectionRepository())
    }
}