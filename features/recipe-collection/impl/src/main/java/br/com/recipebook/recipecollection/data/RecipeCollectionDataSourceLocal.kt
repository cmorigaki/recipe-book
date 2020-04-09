package br.com.recipebook.recipecollection.data

import br.com.recipebook.recipecollection.domain.model.RecipeModel

interface RecipeCollectionDataSourceLocal {
    suspend fun getRecipeCollection(): List<RecipeModel>
    suspend fun saveRecipeCollection(list: List<RecipeModel>)
}