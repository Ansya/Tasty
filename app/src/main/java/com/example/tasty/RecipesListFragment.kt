package com.example.tasty

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tasty.databinding.FragmentListRecipesBinding

class RecipesListFragment: Fragment() {
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

        val categoryId = requireArguments().getInt(ARG_CATEGORY_ID)
        val categoryName = requireArguments().getString(ARG_CATEGORY_NAME)
        val categoryImageURL = requireArguments().getString(ARG_CATEGORY_IMAGE_URL)

        Log.i("[INFO]", "Category ID: ${categoryId} \nCategory Name: ${categoryName} \nCategoryImageURL: ${categoryImageURL}")

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}