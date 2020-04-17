package br.com.recipebook.recipecollection.domain.usecase

import br.com.recipebook.recipecollection.domain.RecipeCollectionRepository
import br.com.recipebook.recipecollection.domain.model.RecipeModel
import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.ResultWrapper

interface GetRecipeCollectionUseCase {
    suspend operator fun invoke(): ResultWrapper<List<RecipeModel>, CommonError>
}

class GetRecipeCollection(
    private val recipeCollectionRepository: RecipeCollectionRepository
) : GetRecipeCollectionUseCase {
    override suspend fun invoke(): ResultWrapper<List<RecipeModel>, CommonError> {
        return recipeCollectionRepository.getRecipeCollection()
    }
}