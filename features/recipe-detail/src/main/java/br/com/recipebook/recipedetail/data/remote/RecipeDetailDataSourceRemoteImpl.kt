package br.com.recipebook.recipedetail.data.remote

import br.com.recipebook.device.LocaleProvider
import br.com.recipebook.recipedetail.data.RecipeDetailDataSourceRemote
import br.com.recipebook.recipedetail.domain.model.IngredientsModel
import br.com.recipebook.recipedetail.domain.model.RecipeDetailModel
import br.com.recipebook.utilityandroid.network.safeApiCall
import br.com.recipebook.utilitykotlin.CommonError
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.map
import kotlinx.coroutines.Dispatchers
import retrofit2.http.GET
import retrofit2.http.Path

internal class RecipeDetailDataSourceRemoteImpl(
    private val api: RecipeDetailApi,
    private val localeProvider: LocaleProvider,
) : RecipeDetailDataSourceRemote {
    override suspend fun getDetail(id: String): Result<RecipeDetailModel, CommonError> {
        return safeApiCall(Dispatchers.IO) {
            api.getData(locale = localeProvider.getLocale(), id = id)
        }.map { mapRecipeToModel(it) }
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
    @GET("{locale}/recipe-detail/{id}.json")
    suspend fun getData(
        @Path("locale") locale: String,
        @Path("id") id: String
    ): RecipeDetailResponse
}
