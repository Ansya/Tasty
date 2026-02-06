package com.example.tasty

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasty.databinding.ItemCategoryBinding

class CategoriesListAdapter(private val categoriesList: List<Category>) :
    RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Category) {
            val drawable =
                try {
                    Drawable.createFromStream(
                    binding.ivCategoryItem.context.assets.open(item.imageURL),
                    null
                )
            } catch (_: Exception) {
                Log.e("[ERROR]", "Category image not found: ${item.imageURL}")
                null
            }
            binding.ivCategoryItem.setImageDrawable(drawable)

            binding.tvCategoryItemTitle.text = item.title
            binding.tvCategoryItemDescription.text = item.description
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemCategoryBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(categoriesList[position])
    }

    override fun getItemCount() = categoriesList.size
}