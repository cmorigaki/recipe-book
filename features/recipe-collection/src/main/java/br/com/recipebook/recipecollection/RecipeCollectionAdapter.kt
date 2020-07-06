package br.com.recipebook.recipecollection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.recipebook.coreandroid.image.ImageResolver
import br.com.recipebook.coreandroid.image.ImageSize
import br.com.recipebook.recipecollection.databinding.RecipeCardBinding
import br.com.recipebook.recipecollection.view.RecipeItem

class RecipeCollectionAdapter(
    private val imageResolver: ImageResolver,
    private val onRecipeClick: (recipeId: String, title: String?) -> Unit
) : RecyclerView.Adapter<RecipeCollectionAdapter.RecipeViewHolder>() {

    private var list = emptyList<RecipeItem>()

    fun setData(list: List<RecipeItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            binding = RecipeCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            imageResolver = imageResolver,
            onRecipeClick = onRecipeClick
        )
    }

    override fun getItemCount() = list.count()

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class RecipeViewHolder(
        private val binding: RecipeCardBinding,
        private val imageResolver: ImageResolver,
        private val onRecipeClick: (recipeId: String, title: String?) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeItem) {
            with(binding) {
                title.text = item.title
                recipeImage.setImageURI(imageResolver.mountUrl(item.imgPath, ImageSize.SMALL))
                root.setOnClickListener { onRecipeClick(item.id, item.title) }
            }
        }
    }
}
