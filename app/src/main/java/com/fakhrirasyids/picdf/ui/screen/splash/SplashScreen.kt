package com.fakhrirasyids.picdf.ui.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fakhrirasyids.picdf.ui.theme.primaryBlue
import com.fakhrirasyids.picdf.utils.UiState
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    navigateToHome: () -> Unit,
    navigateToStarter: () -> Unit,
    splashViewModel: SplashViewModel = koinViewModel()
) {
    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1300)
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(1500)
        if (splashViewModel.isStarterShown.value) {
            navigateToHome()
        } else {
            navigateToStarter()
        }
    }

    Splash(alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .background(primaryBlue)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha = alpha),
            imageVector = Icons.Default.Email,
            contentDescription = "PicDF Logo",
            tint = Color.White
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SplashScreenPreview() {
    Splash(alpha = 1f)
}