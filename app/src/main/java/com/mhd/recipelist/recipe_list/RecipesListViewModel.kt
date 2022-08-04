package com.mhd.recipelist.recipe_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhd.recipelist.domain.Recipe
import com.mhd.recipelist.recipe_list.use_case.GetDateUseCase
import com.mhd.recipelist.recipe_list.use_case.RecipesListUseCase
import com.mhd.recipelist.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    recipesListUseCase: RecipesListUseCase,
    private val getDateUseCase: GetDateUseCase
) : ViewModel() {

    val recipeListState: StateFlow<RecipeListUiState> =
        recipesListUseCase()
            .map(this::handleRecipeData)
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                RecipeListUiState(isLoading = true)
            )

    private fun handleRecipeData(result: Result<List<Recipe>>): RecipeListUiState {
        fun handleSuccess(): RecipeListUiState = when (val dateResult = getDateUseCase("dd MMM")) {
            is Result.Success ->
                RecipeListUiState(
                    recipes = (result as Result.Success).data,
                    currentDate = dateResult.data
                )
            is Result.Error -> RecipeListUiState(
                errorMessage = dateResult.exception?.message ?: "Date pattern is not correct"
            )
            Result.Loading -> throw IllegalStateException("GetDateUseCase don't produce Loading Result")
        }

        return when (result) {
            Result.Loading -> RecipeListUiState(isLoading = true)
            is Result.Success -> handleSuccess()
            is Result.Error -> RecipeListUiState(
                errorMessage = result.exception?.message ?: "Unknown error"
            )
        }
    }
}