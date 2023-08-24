package com.axel.githubbrowser.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.axel.githubbrowser.core.state.AppState
import com.axel.githubbrowser.ui.styles.mediumUnit
import com.axel.githubbrowser.ui.widgets.TopAppBar

@Composable
fun MainApp(appState: AppState) {
  Scaffold(
    snackbarHost = { SnackbarHost(hostState = appState.snackBarHostState) },
    topBar = {
      TopAppBar()
    }) { padding ->
    MainNavHost(
      modifier = Modifier
        .padding(padding)
        .padding(start = mediumUnit, end = mediumUnit),
      navController = appState.navController
    )
  }
}

