package br.com.recipebook.recipecollection.domain

import br.com.recipebook.recipecollection.domain.model.RecipeModel
import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.Result

interface RecipeCollectionRepository {
    suspend fun getRecipeCollection(): Result<List<RecipeModel>, CommonError>
}