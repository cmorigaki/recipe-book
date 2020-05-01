package br.com.recipebook.recipedetail.data.remote

import br.com.recipebook.recipedetail.data.RecipeDetailDataSourceRemote
import br.com.recipebook.recipedetail.domain.model.IngredientsModel
import br.com.recipebook.recipedetail.domain.model.RecipeDetailModel
import br.com.recipebook.utilityandroid.network.safeApiCall
import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.ResultWrapper
import kotlinx.coroutines.Dispatchers
import retrofit2.http.GET
import retrofit2.http.Path

internal class RecipeDetailDataSourceRemoteImpl(
    private val api: RecipeDetailApi
) : RecipeDetailDataSourceRemote {
    override suspend fun getDetail(id: String): ResultWrapper<RecipeDetailModel, CommonError> {
        val result = safeApiCall(Dispatchers.IO) {
            api.getData(id)
        }
        return when (result) {
            is ResultWrapper.Success -> ResultWrapper.Success(mapRecipeToModel(result.data))
            is ResultWrapper.Failure -> ResultWrapper.Failure(result.error)
        }
    }

    private fun mapRecipeToModel(recipe: RecipeDetailResponse) =
        RecipeDetailModel(
            id = recipe.id,
            name = recipe.name,
            imgPath = recipe.imgPath,
            prepTimeMin = recipe.prepTimeMin,
            cookTimeMin = recipe.cookTimeMin,
            ingredients = recipe.ingredients.map {
                IngredientsModel(
                    description = it.description,
                    imgPath = it.imgPath
                )
            },
            instructions = recipe.instructions
        )
}

internal interface RecipeDetailApi {
    @GET("/recipe-detail/{id}.json")
    suspend fun getData(@Path("id") id: String): RecipeDetailResponse
}
