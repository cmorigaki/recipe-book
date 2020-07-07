package br.com.recipebook.recipecollection.domain

import br.com.recipebook.recipecollection.domain.model.RecipeModel
import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.ResultWrapper

interface RecipeCollectionRepository {
    suspend fun getRecipeCollection(): ResultWrapper<List<RecipeModel>, CommonError>
}
