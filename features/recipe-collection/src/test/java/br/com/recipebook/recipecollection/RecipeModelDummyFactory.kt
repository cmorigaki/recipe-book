package br.com.recipebook.recipecollection

import br.com.recipebook.recipecollection.domain.model.RecipeModel

object RecipeModelDummyFactory {
    fun make() = RecipeModel(
        id = "12-3456-7890",
        imgPath = "/any/image.png",
        title = "wololo"
    )

    fun makeList() = mutableListOf<RecipeModel>().apply {
        repeat(3) {
            make()
        }
    }
}