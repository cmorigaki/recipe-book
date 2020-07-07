package br.com.recipebook.recipedetail.domain.usecase

import br.com.recipebook.recipedetail.domain.RecipeDetailRepository
import br.com.recipebook.recipedetail.domain.model.RecipeDetailModel
import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.ResultWrapper

interface GetRecipeDetailUseCase {
    suspend operator fun invoke(id: String): ResultWrapper<RecipeDetailModel, CommonError>
}

class GetRecipeDetail(
    private val recipeDetailRepository: RecipeDetailRepository
) : GetRecipeDetailUseCase {
    override suspend fun invoke(id: String): ResultWrapper<RecipeDetailModel, CommonError> {
        return recipeDetailRepository.getDetail(id)
    }
}
