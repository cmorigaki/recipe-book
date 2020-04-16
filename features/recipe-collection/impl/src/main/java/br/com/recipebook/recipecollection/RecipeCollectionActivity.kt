package br.com.recipebook.recipecollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import br.com.recipebook.recipecollection.databinding.RecipeCollectionActivityBinding
import br.com.recipebook.recipecollection.di.ServiceLocator
import br.com.recipebook.recipecollection.view.RecipeItem
import br.com.recipebook.utilityandroid.MarginItemDecoration
import kotlinx.coroutines.launch
import java.math.BigDecimal
import br.com.recipebook.utilitykotlin.Result

class RecipeCollectionActivity : AppCompatActivity() {

    private val recipeCollectionAdapter by lazy { RecipeCollectionAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RecipeCollectionActivityBinding.inflate(layoutInflater).apply {
            initComponents(this)
            setConcreteList()
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

    private fun setConcreteList() {
        lifecycleScope.launch {
            when (val result = ServiceLocator.getRecipeCollectionRepository().getRecipeCollection()) {
                is Result.Success -> recipeCollectionAdapter.setData(result.data.map {
                    RecipeItem(it.imgPath, it.title, it.category, it.portionSize)
                })
                is Result.Failure -> {
                    recipeCollectionAdapter.setData(emptyList())
                    Toast.makeText(this@RecipeCollectionActivity, result.error::class.java.simpleName, Toast.LENGTH_SHORT).show()
                }
            }
        }
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