package com.axel.githubbrowser.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.axel.githubbrowser.ui.screens.search.SearchScreen

@Composable
fun MainNavHost(modifier: Modifier, navController: NavHostController) {
  NavHost(modifier = modifier, navController = navController, startDestination = "search") {
    composable("search") {
      SearchScreen()
    }
  }
}
