package br.com.recipebook.recipedetail.di

import br.com.recipebook.navigation.Navigator
import br.com.recipebook.recipedetail.data.RecipeDetailDataSourceRemote
import br.com.recipebook.recipedetail.data.RecipeDetailRepositoryImpl
import br.com.recipebook.recipedetail.data.remote.RecipeDetailApi
import br.com.recipebook.recipedetail.data.remote.RecipeDetailDataSourceRemoteImpl
import br.com.recipebook.recipedetail.domain.RecipeDetailRepository
import br.com.recipebook.recipedetail.domain.usecase.GetRecipeDetail
import br.com.recipebook.recipedetail.domain.usecase.GetRecipeDetailUseCase
import br.com.recipebook.recipedetail.presentation.RecipeDetailViewModel
import br.com.recipebook.recipedetail.presentation.RecipeDetailViewState
import br.com.recipebook.recipedetail.view.RecipeDetailNavigator
import br.com.recipebook.recipedetail.view.RecipeDetailSafeArgs
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val recipeDetailViewModule = module {
    factory {
        RecipeDetailNavigator()
    } bind Navigator::class
}

val recipeDetailPresentationModule = module {
    viewModel { (safeArgs: RecipeDetailSafeArgs) ->
        RecipeDetailViewModel(
            safeArgs = safeArgs,
            getRecipeDetail = get(),
            analytics = get()
        )
    }
}

val recipeDetailDomainModule = module {
    factory<GetRecipeDetailUseCase> {
        GetRecipeDetail(
            recipeDetailRepository = get()
        )
    }
}

val recipeDetailDataModule = module {
    factory<RecipeDetailRepository> {
        RecipeDetailRepositoryImpl(
            dataSourceRemote = get()
        )
    }

    factory<RecipeDetailDataSourceRemote> {
        RecipeDetailDataSourceRemoteImpl(
            api = (getKoin().get() as Retrofit).create(RecipeDetailApi::class.java),
            localeProvider = get(),
        )
    }
}

val recipeDetailModules = listOf(
    recipeDetailPresentationModule,
    recipeDetailViewModule,
    recipeDetailDomainModule,
    recipeDetailDataModule
)
