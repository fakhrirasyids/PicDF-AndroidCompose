package com.fakhrirasyids.picdf.ui.screen.starter

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.WormIndicatorType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StarterScreen(navigateToHome: () -> Unit) {
    val pageCount by remember { mutableStateOf(3) }
    val pagerState = rememberPagerState()

    Box(modifier = Modifier.fillMaxSize()) {

        Box(
            Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        ) {
            HorizontalPager(
                pageCount = pageCount,
                contentPadding = PaddingValues(horizontal = 64.dp),
                pageSpacing = 24.dp,
                state = pagerState
            ) { page ->
                StarterContent(page.toString())
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, end = 12.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .clickable { navigateToHome() }
                    .padding(12.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = "Skip", style = TextStyle(fontWeight = FontWeight.Bold))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Back",
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            }
        }

        DotsIndicator(
            modifier = Modifier
                .padding(bottom = 48.dp)
                .align(Alignment.BottomCenter),
            dotCount = pageCount,
            type = WormIndicatorType(
                dotsGraphic = DotGraphic(
                    16.dp,
                    borderWidth = 2.dp,
                    borderColor = MaterialTheme.colorScheme.primary,
                    color = Color.Transparent,
                ),
                wormDotGraphic = DotGraphic(
                    16.dp,
                    color = MaterialTheme.colorScheme.primary,
                )
            ),
            pagerState = pagerState
        )
    }
}

@Composable
fun StarterContent(title: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = title)
    }
}