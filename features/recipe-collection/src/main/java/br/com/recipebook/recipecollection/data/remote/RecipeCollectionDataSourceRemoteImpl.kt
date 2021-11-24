package br.com.recipebook.recipecollection.data.remote

import br.com.recipebook.device.LocaleProvider
import br.com.recipebook.recipecollection.data.RecipeCollectionDataSourceRemote
import br.com.recipebook.recipecollection.domain.model.RecipeModel
import br.com.recipebook.utilityandroid.network.safeApiCall
import br.com.recipebook.utilitykotlin.CommonError
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.map
import kotlinx.coroutines.Dispatchers
import retrofit2.http.GET
import retrofit2.http.Path

internal class RecipeCollectionDataSourceRemoteImpl(
    private val api: RecipeCollectionApi,
    private val localeProvider: LocaleProvider,
) : RecipeCollectionDataSourceRemote {
    override suspend fun getRecipeCollection(): Result<List<RecipeModel>, CommonError> {
        return safeApiCall(Dispatchers.IO) {
            api.getData(localeProvider.getLocale())
        }.map { it.recipeList.map(::mapRecipeToModel) }
    }

    private fun mapRecipeToModel(recipe: RecipeResponse) =
        RecipeModel(
            id = recipe.id,
            imgPath = recipe.imgPath,
            title = recipe.title
        )
}

internal interface RecipeCollectionApi {
    @GET("{locale}/recipeList.json")
    suspend fun getData(
        @Path("locale") locale: String,
    ): RecipeListResponse
}
