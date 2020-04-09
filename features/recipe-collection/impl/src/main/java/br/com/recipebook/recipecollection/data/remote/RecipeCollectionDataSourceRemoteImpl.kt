package br.com.recipebook.recipecollection.data.remote

import br.com.recipebook.recipecollection.data.RecipeCollectionDataSourceRemote
import br.com.recipebook.recipecollection.domain.model.RecipeModel
import br.com.recipebook.utilityandroid.network.safeApiCall
import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.Result
import kotlinx.coroutines.Dispatchers
import retrofit2.http.GET

internal class RecipeCollectionDataSourceRemoteImpl(
    private val api: RecipeCollectionApi
) : RecipeCollectionDataSourceRemote {
    override suspend fun getRecipeCollection(): Result<List<RecipeModel>, CommonError> {
        val result = safeApiCall(Dispatchers.IO) {
            api.getData()
        }
        return when (result) {
            is Result.Success -> Result.Success(result.data.recipeList.map(::mapRecipeToModel))
            is Result.Failure -> Result.Failure(result.error)
        }
    }

    private fun mapRecipeToModel(recipe: RecipeResponse) =
        RecipeModel(
            imgPath = recipe.imgPath,
            title = recipe.title,
            category = recipe.category,
            portionSize = recipe.portions
        )
}

internal interface RecipeCollectionApi {
    @GET("/recipeList.json")
    suspend fun getData(): RecipeListResponse
}
