package br.com.recipebook.settings.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.recipebook.settings.databinding.SettingsListItemBinding
import br.com.recipebook.settings.presentation.model.SettingsItem

class SettingsListAdapter(
    private val onItemClick: (settingsItem: SettingsItem) -> Unit
) : RecyclerView.Adapter<SettingsListAdapter.ViewHolder>() {

    private var list = emptyList<SettingsItem>()

    fun setData(list: List<SettingsItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.ItemViewHolder(
            binding = SettingsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick = onItemClick
        )
    }

    override fun getItemCount() = list.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ViewHolder.ItemViewHolder).bind(list[position])
    }

    sealed class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        class ItemViewHolder(
            private val binding: SettingsListItemBinding,
            private val onItemClick: (settingsItem: SettingsItem) -> Unit
        ) : ViewHolder(binding.root) {

            fun bind(item: SettingsItem) {
                with(binding) {
                    settingsItem.text = root.context.getString(item.title)
                    root.setOnClickListener { onItemClick(item) }
                }
            }
        }
    }
}