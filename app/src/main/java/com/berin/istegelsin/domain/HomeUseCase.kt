package com.berin.istegelsin.domain

import com.berin.istegelsin.data.RecipeRepository
import com.berin.istegelsin.data.RecipeResponse
import com.berin.istegelsin.domain.entity.Category
import com.berin.istegelsin.domain.entity.HomeEntity
import com.berin.istegelsin.domain.entity.Recipes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeUseCase(private val homeRepository: RecipeRepository) {


    fun categories(): Flow<BaseResult<List<Category>,RecipeResponse>>{
        return flow{
            emit(
                BaseResult.Success(
                  homeRepository.getCategory()
                )
            )
        }
    }

    fun recipes(subcategoryID:String):Flow<BaseResult<List<Recipes>,RecipeResponse>>{
        return flow {
            emit(
                BaseResult.Success(
                    homeRepository.getAllRecipe(subcategoryID)
                )
            )
        }

    }
}