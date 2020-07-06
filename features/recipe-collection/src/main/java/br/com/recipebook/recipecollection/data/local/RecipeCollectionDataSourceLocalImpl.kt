package br.com.recipebook.recipecollection.data.local

import br.com.recipebook.recipecollection.data.RecipeCollectionDataSourceLocal
import br.com.recipebook.recipecollection.domain.model.RecipeModel

class RecipeCollectionDataSourceLocalImpl : RecipeCollectionDataSourceLocal {
    override suspend fun getRecipeCollection(): List<RecipeModel> {
        TODO("Not yet implemented")
    }

    override suspend fun saveRecipeCollection(list: List<RecipeModel>) {
        TODO("Not yet implemented")
    }
}
