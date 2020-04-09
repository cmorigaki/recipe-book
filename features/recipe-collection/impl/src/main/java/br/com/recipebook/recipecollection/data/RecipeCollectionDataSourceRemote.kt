package br.com.recipebook.recipecollection.data

import br.com.recipebook.recipecollection.domain.model.RecipeModel
import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.Result

interface RecipeCollectionDataSourceRemote {
    suspend fun getRecipeCollection(): Result<List<RecipeModel>, CommonError>
}