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
import br.com.recipebook.recipecollection.presentation.RecipeCollectionViewModel
import br.com.recipebook.recipecollection.presentation.RecipeCollectionViewState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val recipeCollectionPresentationModule = module {
    viewModel {
        RecipeCollectionViewModel(
            viewState = RecipeCollectionViewState(),
            getRecipeCollection = get(),
            analytics = get(),
            checkInAppUpdate = get(),
        )
    }
}

val recipeCollectionDomainModule = module {
    factory<GetRecipeCollectionUseCase> {
        GetRecipeCollection(
            recipeCollectionRepository = get()
        )
    }
}

val recipeCollectionDataModule = module {
    factory<RecipeCollectionRepository> {
        RecipeCollectionRepositoryImpl(
            dataSourceLocal = get(),
            dataSourceRemote = get()
        )
    }

    factory<RecipeCollectionDataSourceLocal> {
        RecipeCollectionDataSourceLocalImpl()
    }

    factory<RecipeCollectionDataSourceRemote> {
        RecipeCollectionDataSourceRemoteImpl(
            api = (getKoin().get() as Retrofit).create(RecipeCollectionApi::class.java),
            localeProvider = get(),
        )
    }
}

val recipeCollectionModules = listOf(
    recipeCollectionPresentationModule,
    recipeCollectionDomainModule,
    recipeCollectionDataModule
)
