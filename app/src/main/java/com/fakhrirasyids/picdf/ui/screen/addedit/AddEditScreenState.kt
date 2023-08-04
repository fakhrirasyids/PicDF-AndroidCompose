package com.fakhrirasyids.picdf.ui.screen.addedit

import android.graphics.Bitmap

data class AddEditScreenState(
    val imageBitmaps: List<Bitmap> = emptyList(),
    val isLoading: Boolean = false,
    val success: Boolean? = null
)