package com.mhd.recipelist.data


import com.mhd.recipelist.domain.Recipe

data class RecipeResponse(
    val calories: String,
    val carbos: String,
    val description: String,
    val difficulty: Int,
    val fats: String,
    val headline: String,
    val id: String,
    val image: String,
    val name: String,
    val proteins: String,
    val thumb: String,
    val time: String
)

fun RecipeResponse.toRecipe(): Recipe = Recipe(name = name, headLine = headline, imageUrl = image)