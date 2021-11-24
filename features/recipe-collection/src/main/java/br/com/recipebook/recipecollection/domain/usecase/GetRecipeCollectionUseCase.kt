package br.com.recipebook.recipecollection.domain.usecase

import br.com.recipebook.recipecollection.domain.RecipeCollectionRepository
import br.com.recipebook.recipecollection.domain.model.RecipeModel
import br.com.recipebook.utilitykotlin.CommonError
import com.github.michaelbull.result.Result

interface GetRecipeCollectionUseCase {
    suspend operator fun invoke(): Result<List<RecipeModel>, CommonError>
}

class GetRecipeCollection(
    private val recipeCollectionRepository: RecipeCollectionRepository
) : GetRecipeCollectionUseCase {
    override suspend fun invoke(): Result<List<RecipeModel>, CommonError> {
        return recipeCollectionRepository.getRecipeCollection()
    }
}
