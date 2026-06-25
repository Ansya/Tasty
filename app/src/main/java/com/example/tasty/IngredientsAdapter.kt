package com.example.tasty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasty.databinding.ItemIngredientBinding

class IngredientsAdapter(private val ingredientsList: List<Ingredient>) :
    RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    private var quantity = 1

    class ViewHolder(private val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Ingredient) {
            binding.tvIngredientItemTitle.text = item.description

            binding.tvIngredientItemQuantity.text = buildString {
                append(item.quantity)
                append(" ")
                append(item.unitOfMeasure)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemIngredientBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val ingredient = ingredientsList[position]
        val tempQuantity = ingredient.quantity.toFloatOrNull()

        if (tempQuantity != null) {
            val realQuantity = ingredient.quantity.toFloat() * quantity.toFloat()
            var quantityStr = "%.1f".format(realQuantity)

            if (realQuantity % 1.0f == 0.0f) {
                quantityStr = "%.0f".format(realQuantity)
            }

            viewHolder.bind(Ingredient(
                quantityStr,
                ingredient.unitOfMeasure,
                ingredient.description
            ))
        } else {
            viewHolder.bind(ingredient)
        }
    }

    override fun getItemCount() = ingredientsList.size

    fun updateIngredients(progress: Int) {
        quantity = progress
        notifyDataSetChanged()
    }
}