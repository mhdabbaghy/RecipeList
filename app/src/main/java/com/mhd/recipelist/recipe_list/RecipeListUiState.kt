package com.mhd.recipelist.recipe_list

import com.mhd.recipelist.domain.Recipe

data class RecipeListUiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val currentDate: String = "",
    val errorMessage: String? = null
)
