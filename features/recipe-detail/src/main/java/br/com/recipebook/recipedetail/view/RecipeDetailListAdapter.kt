package br.com.recipebook.recipedetail.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.recipebook.coreandroid.image.ImageResolver
import br.com.recipebook.coreandroid.image.ImageSize
import br.com.recipebook.recipedetail.R
import br.com.recipebook.recipedetail.databinding.RecipeDetailListItemBinding
import br.com.recipebook.recipedetail.databinding.RecipeDetailListItemHeaderBinding
import br.com.recipebook.recipedetail.presentation.model.DescriptionItem
import br.com.recipebook.recipedetail.presentation.model.IngredientHeaderItem
import br.com.recipebook.recipedetail.presentation.model.InstructionHeaderItem
import br.com.recipebook.recipedetail.presentation.model.RecipeDetailItem

private enum class ViewType {
    HEADER_INGREDIENTS,
    DESCRIPTION,
    HEADER_INSTRUCTIONS
}

class RecipeDetailListAdapter(
    private val imageResolver: ImageResolver
) : RecyclerView.Adapter<RecipeDetailListAdapter.ViewHolder>() {

    private var list = emptyList<RecipeDetailItem>()

    fun setData(list: List<RecipeDetailItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ViewType.HEADER_INGREDIENTS.ordinal, ViewType.HEADER_INSTRUCTIONS.ordinal ->
                ViewHolder.HeaderViewHolder(
                    binding = RecipeDetailListItemHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            ViewType.DESCRIPTION.ordinal ->
                ViewHolder.DescriptionViewHolder(
                    binding = RecipeDetailListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    imageResolver = imageResolver
                )
            else -> throw ClassNotFoundException()
        }
    }

    override fun getItemCount() = list.count()

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is IngredientHeaderItem -> ViewType.HEADER_INGREDIENTS.ordinal
            is DescriptionItem -> ViewType.DESCRIPTION.ordinal
            is InstructionHeaderItem -> ViewType.HEADER_INSTRUCTIONS.ordinal
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ViewType.HEADER_INGREDIENTS.ordinal -> (holder as ViewHolder.HeaderViewHolder).bind(
                holder.itemView.context.getString(R.string.recipeDetailIngredients)
            )
            ViewType.HEADER_INSTRUCTIONS.ordinal -> (holder as ViewHolder.HeaderViewHolder).bind(
                holder.itemView.context.getString(R.string.recipeDetailInstruction)
            )
            ViewType.DESCRIPTION.ordinal ->
                (holder as ViewHolder.DescriptionViewHolder).bind(
                    list[position] as DescriptionItem
                )
            else -> throw ClassNotFoundException()
        }
    }

    sealed class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        class HeaderViewHolder(
            private val binding: RecipeDetailListItemHeaderBinding
        ) : ViewHolder(binding.root) {

            fun bind(headerText: String) {
                with(binding) {
                    recipeDetailItemHeader.text = headerText
                }
            }
        }

        class DescriptionViewHolder(
            private val binding: RecipeDetailListItemBinding,
            private val imageResolver: ImageResolver
        ) : ViewHolder(binding.root) {

            fun bind(item: DescriptionItem) {
                with(binding) {

                    if (!item.imgPath.isNullOrBlank()) {
                        recipeDetailItemImage.setImageURI(
                            imageResolver.mountUrl(
                                item.imgPath,
                                ImageSize.SMALL
                            )
                        )
                        recipeDetailItemImage.visibility = View.VISIBLE
                    } else {
                        recipeDetailItemImage.visibility = View.INVISIBLE
                    }
                    recipeDetailItemDescription.text = item.description
                }
            }
        }
    }
}
