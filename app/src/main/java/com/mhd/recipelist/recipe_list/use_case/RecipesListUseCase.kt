package com.mhd.recipelist.recipe_list.use_case

import com.mhd.recipelist.data.APIService
import com.mhd.recipelist.data.toRecipe
import com.mhd.recipelist.di.Dispatcher
import com.mhd.recipelist.di.Dispatchers
import com.mhd.recipelist.domain.Recipe
import com.mhd.recipelist.result.Result
import com.mhd.recipelist.result.asResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipesListUseCase @Inject constructor(
    private val apiService: APIService,
    @Dispatcher(Dispatchers.IO) private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<Result<List<Recipe>>> =
        flow {
            emit(apiService.getRecipes())
        }.map { responseList ->
            responseList.map { it.toRecipe() }
        }.asResult().flowOn(dispatcher)

}