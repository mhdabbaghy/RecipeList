package com.mhd.recipelist.recipe_list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.mhd.recipelist.R
import com.mhd.recipelist.databinding.FragmentRecipesListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesListFragment : Fragment(R.layout.fragment_recipes_list) {
    private var _binding: FragmentRecipesListBinding? = null
    private val binding: FragmentRecipesListBinding
        get() = _binding!!

    private val viewModel: RecipesListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRecipesListBinding.bind(view)
        collectState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun collectState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recipeListState.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(state: RecipeListUiState) {
        binding.progressCircular.isVisible = state.isLoading
        if (state.recipes.isNotEmpty() && state.currentDate.isNotEmpty()) {
            val adapter = RecipeListAdapter(state.currentDate, state.recipes)
            binding.rvRecipes.adapter = adapter
        }

        if (state.errorMessage.isNullOrEmpty().not()) {
            Snackbar.make(binding.root, state.errorMessage!!, Snackbar.LENGTH_LONG).show()
        }
    }

}