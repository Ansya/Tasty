package com.example.tasty

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.RecyclerView
import com.example.tasty.databinding.FragmentListRecipesBinding

class RecipesListFragment : Fragment() {
    private var _binding: FragmentListRecipesBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for FragmentListRecipesBinding must not be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTitle()

        initRecycle()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initTitle() {
        val categoryName = arguments?.getString(ARG_CATEGORY_NAME) ?: ""
        val categoryImageUrl = arguments?.getString(ARG_CATEGORY_IMAGE_URL)
        if (categoryImageUrl != null) {
            val drawable =
                try {
                    Drawable.createFromStream(
                        binding.rvRecipesCategoryImage.context.assets.open(categoryImageUrl),
                        null
                    )
                } catch (_: Exception) {
                    Log.e("[ERROR]", "Category image not found: ${categoryImageUrl}")
                    null
                }
            binding.rvRecipesCategoryImage.setImageDrawable(drawable)
        } else {
            Log.e("[ERROR]", "Category image not set.")
        }

        binding.rvRecipesCategoryTitle.text = categoryName
    }

    fun initRecycle() {
        val categoryId = arguments?.getInt(ARG_CATEGORY_ID) ?: 0

        val recipesListAdapter = RecipesListAdapter(STUB.getRecipesByCategoryId(categoryId))

        val recyclerView: RecyclerView = binding.rvRecipes
        recyclerView.adapter = recipesListAdapter

        val listener = object : RecipesListAdapter.OnRecipeClickListener {
            override fun onRecipeClick(recipeId: Int) {
                openRecipeByRecipeId(recipeId)
            }
        }
        recipesListAdapter.setOnRecipeClickListener(listener)
    }

    private fun openRecipeByRecipeId(recipeId: Int) {
        val recipe = STUB.getRecipeById(recipeId)
        val bundle = bundleOf(ARG_RECIPE to recipe)

        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<RecipeFragment>(R.id.mainContainer, args = bundle)
            addToBackStack(null)
        }
    }
}
