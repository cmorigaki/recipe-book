package br.com.recipebook.recipecollection.domain

import br.com.recipebook.recipecollection.domain.model.RecipeModel
import br.com.recipebook.utilitykotlin.CommonError
import com.github.michaelbull.result.Result

interface RecipeCollectionRepository {
    suspend fun getRecipeCollection(): Result<List<RecipeModel>, CommonError>
}
