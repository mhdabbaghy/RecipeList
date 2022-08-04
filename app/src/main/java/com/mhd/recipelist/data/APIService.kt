package com.mhd.recipelist.data

import retrofit2.http.GET

interface APIService {

    @GET("android-test/recipes.json")
    suspend fun getRecipes(): List<RecipeResponse>
}