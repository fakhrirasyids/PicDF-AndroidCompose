package com.fakhrirasyids.picdf.ui.screen.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhrirasyids.picdf.utils.Constants
import com.fakhrirasyids.picdf.utils.Constants.preferenceDefaultValue
import com.fakhrirasyids.picdf.utils.UiState
import com.fakhrirasyids.picdf.utils.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel(private val userPreferences: UserPreferences) : ViewModel() {
    private val _isStarterShown: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val isStarterShown: StateFlow<Boolean>
        get() = _isStarterShown

    init {
        viewModelScope.launch {
            userPreferences.isStarterShown().collect { response ->
                if (response == preferenceDefaultValue) {
                    setIsStarterShown()
                } else {
                    _isStarterShown.value = true
                }
            }
        }
    }

    private fun setIsStarterShown() {
        viewModelScope.launch {
            userPreferences.setIsStarterShown()
            _isStarterShown.value = false
        }
    }
}