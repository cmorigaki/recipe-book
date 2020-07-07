package br.com.recipebook.recipecollection.data

import br.com.recipebook.recipecollection.domain.RecipeCollectionRepository
import br.com.recipebook.recipecollection.domain.model.RecipeModel
import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.ResultWrapper

internal class RecipeCollectionRepositoryImpl constructor(
    private val dataSourceLocal: RecipeCollectionDataSourceLocal,
    private val dataSourceRemote: RecipeCollectionDataSourceRemote
) : RecipeCollectionRepository {
    override suspend fun getRecipeCollection(): ResultWrapper<List<RecipeModel>, CommonError> {
        return dataSourceRemote.getRecipeCollection()
    }
}
