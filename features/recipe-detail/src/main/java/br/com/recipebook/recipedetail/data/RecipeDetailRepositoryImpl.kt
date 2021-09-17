package br.com.recipebook.recipedetail.data

import br.com.recipebook.recipedetail.domain.RecipeDetailRepository
import br.com.recipebook.recipedetail.domain.model.RecipeDetailModel
import br.com.recipebook.utilitykotlin.CommonError
import com.github.michaelbull.result.Result

class RecipeDetailRepositoryImpl(
    private val dataSourceRemote: RecipeDetailDataSourceRemote
) : RecipeDetailRepository {
    override suspend fun getDetail(id: String): Result<RecipeDetailModel, CommonError> {
        return dataSourceRemote.getDetail(id)
    }
}
