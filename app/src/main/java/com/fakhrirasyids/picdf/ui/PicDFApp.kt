package com.fakhrirasyids.picdf.ui

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fakhrirasyids.picdf.ui.navigation.Screen
import com.fakhrirasyids.picdf.ui.screen.home.HomeScreen
import com.fakhrirasyids.picdf.ui.screen.splash.SplashScreen
import com.fakhrirasyids.picdf.ui.screen.starter.StarterScreen
import com.fakhrirasyids.picdf.ui.theme.primaryBlue
import com.fakhrirasyids.picdf.ui.theme.toggleStatusBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PicDFApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            if (currentRoute == Screen.Home.route) {
                FloatingActionButton(
                    containerColor = primaryBlue,
                    onClick = { },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                )
            }
        },
    ) {
        NavHost(navController = navController, startDestination = Screen.Splash.route) {
            composable(route = Screen.Splash.route) {
                toggleStatusBar(isTransparent = false)
                SplashScreen(navigateToStarter = {
                    navController.navigate(Screen.Starter.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                })
            }

            composable(route = Screen.Starter.route) {
                toggleStatusBar(isTransparent = true)
                StarterScreen(navigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Starter.route) {
                            inclusive = true
                        }
                    }
                })
            }

            composable(route = Screen.Home.route) {
                toggleStatusBar(isTransparent = true)
                HomeScreen()
            }
        }
    }
}