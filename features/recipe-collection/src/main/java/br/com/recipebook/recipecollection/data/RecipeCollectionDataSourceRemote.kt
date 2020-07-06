package br.com.recipebook.recipecollection.data

import br.com.recipebook.recipecollection.domain.model.RecipeModel
import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.ResultWrapper

interface RecipeCollectionDataSourceRemote {
    suspend fun getRecipeCollection(): ResultWrapper<List<RecipeModel>, CommonError>
}
