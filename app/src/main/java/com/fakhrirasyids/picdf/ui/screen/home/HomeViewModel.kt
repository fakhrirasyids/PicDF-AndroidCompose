package com.fakhrirasyids.picdf.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhrirasyids.picdf.utils.Constants.preferenceDefaultValue
import com.fakhrirasyids.picdf.utils.UiState
import com.fakhrirasyids.picdf.utils.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {


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