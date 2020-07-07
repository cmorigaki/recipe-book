package br.com.recipebook.recipecollection.domain.usecase

import br.com.recipebook.recipecollection.RecipeModelDummyFactory
import br.com.recipebook.recipecollection.domain.RecipeCollectionRepository
import br.com.recipebook.utilitykotlin.ResultWrapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetRecipeCollectionTest {
    private val recipeCollectionRepository: RecipeCollectionRepository = mockk()

    private val getRecipeCollection = GetRecipeCollection(recipeCollectionRepository)

    @Test
    fun `invoke proxy call to repository`() = runBlocking {
        coEvery { recipeCollectionRepository.getRecipeCollection() } returns ResultWrapper.Success(
            RecipeModelDummyFactory.makeList()
        )

        getRecipeCollection.invoke()

        coVerify(exactly = 1) { recipeCollectionRepository.getRecipeCollection() }
    }
}
