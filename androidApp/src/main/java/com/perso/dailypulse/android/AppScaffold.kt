package com.perso.dailypulse.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.perso.dailypulse.android.screens.AboutScreen
import com.perso.dailypulse.android.screens.ArticlesScreen
import com.perso.dailypulse.android.screens.Screens
import com.perso.dailypulse.android.screens.SourcesScreen

@Composable
fun AppScaffold() {
    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        )
    }

}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.ARTICLES.route,
        modifier = modifier,
    ) {
        composable(Screens.ARTICLES.route) {
            ArticlesScreen(
                onAboutClick = { navController.navigate(Screens.ABOUT_DEVICE.route) },
                onSourcesClick = {
                    navController.navigate(Screens.SOURCES.route)
                })
        }
        composable(Screens.ABOUT_DEVICE.route) {
            AboutScreen(onUpClick = { navController.popBackStack() })
        }
        composable(Screens.SOURCES.route) {
            SourcesScreen()
        }
    }

}