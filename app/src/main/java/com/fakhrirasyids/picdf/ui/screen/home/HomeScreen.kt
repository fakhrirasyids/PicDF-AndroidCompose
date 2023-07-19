package com.fakhrirasyids.picdf.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fakhrirasyids.picdf.R
import com.fakhrirasyids.picdf.ui.components.CustomSearchBar
import com.fakhrirasyids.picdf.ui.theme.primaryBlue
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        HeaderContent(modifier = modifier, viewModel = viewModel)
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun HeaderContent(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val query by viewModel.query

    val checkedGridState = remember { mutableStateOf(false) }
    val checkedListState = remember { mutableStateOf(true) }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            modifier = modifier
                .padding(start = 12.dp)
                .weight(1F),
            text = "Your Generated Documents",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 28.sp)
        )

        Icon(
            modifier = modifier
                .clickable { }
                .padding(12.dp),
            imageVector = Icons.Default.Settings,
            contentDescription = "Settings"
        )
    }

    Spacer(modifier = modifier.padding(8.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomSearchBar(
            query = query,
            onQueryChange = viewModel::setSearchedQuery,
            modifier = modifier.weight(4F)
        )

        Row(
            modifier = modifier
                .weight(1.3F)
                .padding(start = 12.dp),
        ) {
            IconToggleButton(
                checked = checkedGridState.value,
                enabled = !checkedGridState.value,
                onCheckedChange = {
                    checkedGridState.value = !checkedGridState.value
                    checkedListState.value = !checkedListState.value
                },
                modifier = Modifier
                    .padding(2.dp)
                    .weight(1F),
            ) {
                val transition = updateTransition(checkedGridState.value, label = "gridTransition")

                val tint by transition.animateColor(label = "listTransition") { isChecked ->
                    if (isChecked) primaryBlue else Color.DarkGray
                }

                val size by transition.animateDp(
                    transitionSpec = {
                        if (false isTransitioningTo true) {
                            keyframes {
                                durationMillis = 250
                                30.dp at 0 with LinearOutSlowInEasing
                                35.dp at 15 with FastOutLinearInEasing
                                40.dp at 75
                                35.dp at 150
                            }
                        } else {
                            spring(stiffness = Spring.StiffnessVeryLow)
                        }
                    },
                    label = "gridSize"
                ) { 30.dp }

                Icon(
                    painter = if (checkedGridState.value) painterResource(id = R.drawable.ic_grid) else painterResource(
                        id = R.drawable.ic_grid_outlined
                    ),
                    contentDescription = "Grid View",
                    tint = tint,
                    modifier = Modifier.size(size)
                )
            }

            Spacer(modifier = modifier.padding(2.dp))

            IconToggleButton(
                checked = checkedListState.value,
                enabled = !checkedListState.value,
                onCheckedChange = {
                    checkedListState.value = !checkedListState.value
                    checkedGridState.value = !checkedGridState.value
                },
                modifier = Modifier
                    .padding(2.dp)
                    .weight(1F),
            ) {
                val transition = updateTransition(checkedListState.value, label = "listTransition")

                val tint by transition.animateColor(label = "listColor") { isChecked ->
                    if (isChecked) primaryBlue else Color.DarkGray
                }

                val size by transition.animateDp(
                    transitionSpec = {
                        if (false isTransitioningTo true) {
                            keyframes {
                                durationMillis = 250
                                30.dp at 0 with LinearOutSlowInEasing
                                35.dp at 15 with FastOutLinearInEasing
                                40.dp at 75
                                35.dp at 150
                            }
                        } else {
                            spring(stiffness = Spring.StiffnessVeryLow)
                        }
                    },
                    label = "listSize"
                ) { 30.dp }

                Icon(
                    painter = if (checkedListState.value) painterResource(id = R.drawable.ic_list) else painterResource(
                        id = R.drawable.ic_list_outlined
                    ),
                    contentDescription = "List Mode",
                    tint = tint,
                    modifier = Modifier.size(size)
                )
            }
        }
    }
}