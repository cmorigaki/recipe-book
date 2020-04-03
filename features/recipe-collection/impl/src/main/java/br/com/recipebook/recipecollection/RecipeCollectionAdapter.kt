package br.com.recipebook.recipecollection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import br.com.recipebook.recipecollection.databinding.RecipeCardBinding
import br.com.recipebook.recipecollection.view.RecipeItem

class RecipeCollectionAdapter : RecyclerView.Adapter<RecipeCollectionAdapter.RecipeViewHolder>() {

    private var list = emptyList<RecipeItem>()

    fun setData(list: List<RecipeItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            RecipeCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.count()

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class RecipeViewHolder(private val binding: RecipeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeItem) {
            with(binding) {
                title.text = item.title
                category.text = item.category
                recipeSize.text =
                    root.context.getString(R.string.portion_size, item.portionSize.toString())
            }
        }
    }
}