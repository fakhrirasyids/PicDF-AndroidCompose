package com.fakhrirasyids.picdf.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(
) : ViewModel() {

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun setSearchedQuery(newQuery: String) {
        viewModelScope.launch {
            _query.value = newQuery
//            treepRepository.searchTreep(_query.value)
//                .collect { listLocation ->
//                    _uiState.value = UiState.Success(listLocation.sortedBy { it.title })
//                }
        }
    }
}