package com.berin.istegelsin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berin.istegelsin.domain.BaseResult
import com.berin.istegelsin.domain.HomeUseCase
import com.berin.istegelsin.domain.entity.Category
import com.berin.istegelsin.domain.entity.RecipeSearch
import com.berin.istegelsin.domain.entity.Recipes
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val homeUseCase: HomeUseCase) : ViewModel() {
    private val _homeState:MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState.idle)
    val homeState: StateFlow<HomeUIState> = _homeState

    fun getRecipes(catId: String,keyword:String){
        viewModelScope.launch {
            homeUseCase.recipes(catId,keyword)
                .onStart { _homeState.value=HomeUIState.Loading }
                .catch { e->_homeState.value = HomeUIState.Error(e.message) }
                .collect { base->
                    when(base){
                        is BaseResult.Success -> _homeState.value = HomeUIState.Success(base.data)
                    }
                }
        }
    }

    fun getCategories(){
        viewModelScope.launch {
            homeUseCase.categories()
                .onStart { _homeState.value=HomeUIState.Loading }
                .catch { e->_homeState.value=HomeUIState.Error(e.message) }
                .collect { base ->
                    when(base){
                        is BaseResult.Success -> _homeState.value = HomeUIState.PageSuccessCategory(base.data)
                    }
                }
        }
    }
}

sealed class HomeUIState {
    data class Success(val recipeList: List<Recipes>) : HomeUIState()
    data class Error(val error: String?) : HomeUIState()
    data class PageSuccessCategory(val categoryList: List<Category>) : HomeUIState()

    object idle : HomeUIState()
    object Loading : HomeUIState()
}