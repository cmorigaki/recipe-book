package br.com.recipebook.recipedetail.data

import br.com.recipebook.recipedetail.domain.model.RecipeDetailModel
import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.ResultWrapper

interface RecipeDetailDataSourceRemote {
    suspend fun getDetail(id: String): ResultWrapper<RecipeDetailModel, CommonError>
}
