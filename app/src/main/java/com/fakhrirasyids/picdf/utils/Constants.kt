package com.fakhrirasyids.picdf.utils

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    const val preferenceName: String = "user_preference"
    const val preferenceDefaultValue: String = "preference_default_value"

    const val IS_STARTER_SHOWN = "is_starter_shown"
    val isStarterShown = stringPreferencesKey(IS_STARTER_SHOWN)

    const val AUTHORITY_APP = "com.fakhrirasyids.picdf.shareFile"

}