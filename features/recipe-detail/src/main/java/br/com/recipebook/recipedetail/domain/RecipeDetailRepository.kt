package br.com.recipebook.recipedetail.domain

import br.com.recipebook.recipedetail.domain.model.RecipeDetailModel
import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.ResultWrapper

interface RecipeDetailRepository {
    suspend fun getDetail(id: String): ResultWrapper<RecipeDetailModel, CommonError>
}