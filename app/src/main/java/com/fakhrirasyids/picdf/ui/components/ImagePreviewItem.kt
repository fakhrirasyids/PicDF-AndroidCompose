package com.fakhrirasyids.picdf.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Preview
@Composable
fun ImagePreviewItem(
    bitmap: Bitmap? = null,
    onRemoveClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        AsyncImage(
            model = bitmap,
            contentDescription = null,
        )
//        Button(
//            onClick = onRemoveClick,
//            colors = buttonColors(containerColor = Color.Red, contentColor = Color.White),
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        ) {
//            Text(text = "Remove")
//        }
    }
}