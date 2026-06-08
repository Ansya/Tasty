package com.example.tasty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.RecyclerView
import com.example.tasty.databinding.FragmentListCategoriesBinding

class CategoriesListFragment : Fragment() {

    private var _binding: FragmentListCategoriesBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for FragmentListCategoriesBinding must not be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecycler() {
        val categoriesListAdapter = CategoriesListAdapter(STUB.getCategories())

        val recyclerView: RecyclerView = binding.rvCategories
        recyclerView.adapter = categoriesListAdapter

        val listener = object : CategoriesListAdapter.OnItemClickListener {
            override fun onItemClick(categoryID: Int) {
                openRecipesByCategoryId(categoryID)
            }
        }
        categoriesListAdapter.setOnItemClickListener(listener)
    }

    private fun openRecipesByCategoryId(categoryID: Int) {
        val category = STUB.getCategories().find { it.id == categoryID }
        val categoryName = category?.title
        val categoryImageUrl = category?.imageURL

        val bundle = Bundle().apply {
            putInt(ARG_CATEGORY_ID, categoryID)
            putString(ARG_CATEGORY_NAME, categoryName)
            putString(ARG_CATEGORY_IMAGE_URL, categoryImageUrl)
        }

        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<RecipesListFragment>(R.id.mainContainer, args = bundle)
            addToBackStack(null)
        }
    }
}
