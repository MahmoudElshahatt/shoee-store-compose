package com.shahtott.shoee_store_compose.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    private val _homeState = mutableStateOf(HomeState())
    val homeState: State<HomeState> = _homeState

    fun onRefreshHome() {

    }

    fun onQueryChange(query: String) {
        _homeState.value = _homeState.value.copy(query = query)
    }

}

data class HomeState(
    val sliders: List<Slider> = emptyList(),
    val categories: String = "",
    val products: String = "",
    val query: String = "",
)

data class Slider(
    val image: String
)
