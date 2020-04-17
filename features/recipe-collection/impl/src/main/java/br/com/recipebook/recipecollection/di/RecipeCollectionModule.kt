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
import org.koin.dsl.module
import retrofit2.Retrofit

val recipeCollectionPresentationModule = module {

}

val recipeCollectionDomainModule = module {
    single<GetRecipeCollectionUseCase> {
        GetRecipeCollection(
            recipeCollectionRepository = get()
        )
    }
}

val recipeCollectionDataModule = module {
    single<RecipeCollectionRepository> {
        RecipeCollectionRepositoryImpl(
            dataSourceLocal = get(),
            dataSourceRemote = get()
        )
    }

    single<RecipeCollectionDataSourceLocal> {
        RecipeCollectionDataSourceLocalImpl()
    }

    single<RecipeCollectionDataSourceRemote> {
        RecipeCollectionDataSourceRemoteImpl(
            api = (getKoin().get() as Retrofit).create(RecipeCollectionApi::class.java)
        )
    }
}

val recipeCollectionModules = listOf(
    recipeCollectionPresentationModule,
    recipeCollectionDomainModule,
    recipeCollectionDataModule
)