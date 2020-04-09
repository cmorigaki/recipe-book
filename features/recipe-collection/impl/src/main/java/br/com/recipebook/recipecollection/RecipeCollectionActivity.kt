package br.com.recipebook.recipecollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.recipebook.recipecollection.databinding.RecipeCollectionActivityBinding
import br.com.recipebook.recipecollection.view.RecipeItem
import br.com.recipebook.utilityandroid.MarginItemDecoration
import java.math.BigDecimal

class RecipeCollectionActivity : AppCompatActivity() {

    private val recipeCollectionAdapter by lazy { RecipeCollectionAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RecipeCollectionActivityBinding.inflate(layoutInflater).apply {
            initComponents(this)
            mockList()
            setContentView(root)
        }
    }

    private fun initComponents(binding: RecipeCollectionActivityBinding) {
        binding.recipeList.adapter = recipeCollectionAdapter
        binding.recipeList.addItemDecoration(
            MarginItemDecoration(
                resources.getDimension(R.dimen.margin_components_default).toInt()
            )
        )
    }

    private fun mockList() {
        val list = arrayListOf(
            RecipeItem(
                imgPath = null,
                title = "Feijoada",
                category = "Brasileira",
                portionSize = BigDecimal(4)
            ),
            RecipeItem(
                imgPath = null,
                title = "Frango com purê",
                category = "Brasileira",
                portionSize = BigDecimal(2)
            ),
            RecipeItem(
                imgPath = null,
                title = "Sashimi",
                category = "Japonesa",
                portionSize = BigDecimal(6)
            ),
            RecipeItem(
                imgPath = null,
                title = "Macarrão a bolonhesa",
                category = "Italiana",
                portionSize = BigDecimal(4)
            ),
            RecipeItem(
                imgPath = null,
                title = "Hamburguer Gordo",
                category = "Americana",
                portionSize = BigDecimal(1)
            )
        )
        recipeCollectionAdapter.setData(list)
    }
}