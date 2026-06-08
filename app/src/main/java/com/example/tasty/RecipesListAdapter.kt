package com.example.tasty

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasty.databinding.ItemRecipeBinding

class RecipesListAdapter(private val recipesList: List<Recipe>) :
    RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {

    interface OnRecipeClickListener {
        fun onRecipeClick(recipeID: Int)
    }

    private var recipeClickListener : OnRecipeClickListener? = null

    fun setOnRecipeClickListener (listener: OnRecipeClickListener) {
        recipeClickListener = listener
    }

    class ViewHolder(private val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Recipe) {
            val drawable =
                try {
                    Drawable.createFromStream(
                        binding.ivRecipeItem.context.assets.open(item.imageURL),
                        null
                    )
                } catch (_: Exception) {
                    Log.e("[ERROR]", "Category image not found: ${item.imageURL}")
                    null
                }
            binding.ivRecipeItem.setImageDrawable(drawable)

            binding.tvRecipeItemTitle.text = item.title
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemRecipeBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(recipesList[position])

        viewHolder.itemView.setOnClickListener {
            recipeClickListener?.onRecipeClick(recipesList[position].id)
        }
    }

    override fun getItemCount() = recipesList.size
}
