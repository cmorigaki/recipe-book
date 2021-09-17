package br.com.recipebook.recipecollection.data

import br.com.recipebook.recipecollection.domain.RecipeCollectionRepository
import br.com.recipebook.recipecollection.domain.model.RecipeModel
import br.com.recipebook.utilitykotlin.CommonError
import com.github.michaelbull.result.Result

internal class RecipeCollectionRepositoryImpl constructor(
    private val dataSourceLocal: RecipeCollectionDataSourceLocal,
    private val dataSourceRemote: RecipeCollectionDataSourceRemote
) : RecipeCollectionRepository {
    override suspend fun getRecipeCollection(): Result<List<RecipeModel>, CommonError> {
        return dataSourceRemote.getRecipeCollection()
    }
}
