package br.com.recipebook.recipedetail.di

import br.com.recipebook.navigation.Navigator
import br.com.recipebook.recipedetail.presentation.RecipeDetailViewModel
import br.com.recipebook.recipedetail.presentation.RecipeDetailViewState
import br.com.recipebook.recipedetail.view.RecipeDetailNavigator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val recipeDetailPresentationModule = module {
    viewModel {
        RecipeDetailViewModel(
            viewState = RecipeDetailViewState()
        )
    }
}

val recipeDetailViewModule = module {
    single<Navigator> {
        RecipeDetailNavigator()
    } bind Navigator::class
}


val recipeDetailModules = listOf(
    recipeDetailPresentationModule,
    recipeDetailViewModule
)