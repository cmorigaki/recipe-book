package br.com.recipebook.recipedetail.domain

import br.com.recipebook.recipedetail.domain.model.RecipeDetailModel
import br.com.recipebook.utilitykotlin.CommonError
import com.github.michaelbull.result.Result

interface RecipeDetailRepository {
    suspend fun getDetail(id: String): Result<RecipeDetailModel, CommonError>
}
