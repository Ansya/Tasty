package com.example.tasty

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasty.databinding.FragmentRecipeBinding
import com.google.android.material.divider.MaterialDividerItemDecoration

class RecipeFragment : Fragment() {
    private var _binding: FragmentRecipeBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for FragmentRecipeBinding must not be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipe = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(ARG_RECIPE, Recipe::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable<Recipe>(ARG_RECIPE)
        }

        if (recipe != null) {
            initUI(recipe)
            initRecycle(recipe)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initUI(recipe: Recipe) {
        val drawable =
            try {
                Drawable.createFromStream(
                    binding.imRecipeImage.context.assets.open(recipe.imageUrl),
                    null
                )
            } catch (_: Exception) {
                Log.e("[ERROR]", "Category image not found: ${recipe.imageUrl}")
                null
            }
        binding.imRecipeImage.setImageDrawable(drawable)
        binding.tvRecipeTitle.text = recipe.title
    }

    fun initRecycle(recipe: Recipe) {
        val ingredientsDivider = MaterialDividerItemDecoration(
            requireContext(),
            LinearLayoutManager.VERTICAL
        )
        //ingredientsDivider.setDividerInsetStartResource(requireContext(),R.dimen.recipe_padding)
        //ingredientsDivider.setDividerInsetEndResource(requireContext(),R.dimen.recipe_padding)
        ingredientsDivider.isLastItemDecorated = false

        val ingredientsAdapter = IngredientsAdapter(recipe.ingredients)
        val ingredientsRecyclerView: RecyclerView = binding.rvIngredients
        ingredientsRecyclerView.adapter = ingredientsAdapter
        ingredientsRecyclerView.addItemDecoration(ingredientsDivider)


        val methodDivider = MaterialDividerItemDecoration(
            requireContext(),
            LinearLayoutManager.VERTICAL
        )
        //methodDivider.setDividerInsetStartResource(requireContext(),R.dimen.recipe_padding)
        //methodDivider.setDividerInsetEndResource(requireContext(),R.dimen.recipe_padding)
        methodDivider.isLastItemDecorated = false

        val methodAdapter = MethodAdapter(recipe.method)
        val methodRecyclerView = binding.rvMethod
        methodRecyclerView.adapter = methodAdapter
        methodRecyclerView.addItemDecoration(methodDivider)
    }
}
