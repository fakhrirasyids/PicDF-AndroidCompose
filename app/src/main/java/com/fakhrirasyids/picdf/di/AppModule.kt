package com.fakhrirasyids.picdf.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.fakhrirasyids.picdf.ui.screen.addedit.AddEditViewModel
import com.fakhrirasyids.picdf.ui.screen.home.HomeViewModel
import com.fakhrirasyids.picdf.ui.screen.splash.SplashViewModel
import com.fakhrirasyids.picdf.utils.Constants
import com.fakhrirasyids.picdf.utils.UserPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.preferenceName)

val preferencesModule = module {
    single {
        UserPreferences.getInstance(androidContext().dataStore)
    }
}

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { AddEditViewModel() }
    viewModel { SplashViewModel(get()) }
}