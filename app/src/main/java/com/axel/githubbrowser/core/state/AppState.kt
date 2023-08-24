package com.axel.githubbrowser.core.state

import android.content.res.Resources
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun rememberAppState(
  snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
  navController: NavHostController = rememberNavController(),
  snackbarManager: SnackbarManager = SnackbarManager,
  resources: Resources = resources(),
  coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
  remember(snackBarHostState, navController, snackbarManager,resources, coroutineScope) {
    AppState(snackBarHostState, navController, snackbarManager, resources,coroutineScope)
  }


@Stable
class AppState(
  val snackBarHostState: SnackbarHostState,
  val navController: NavHostController,
  private val snackbarManager: SnackbarManager,
  private val resources: Resources,
  private val coroutineScope: CoroutineScope
){
  init {
    coroutineScope.launch {
      SnackbarManager.messages.collect { currentMessages ->
        if (currentMessages.isNotEmpty()) {
          val message = currentMessages[0]
          val text = resources.getText(message.messageId)
          //Display the snack on the screen. `showSnackbar` is a function
          //that suspends until the snackbar disappears from the screen
          snackBarHostState.showSnackbar(text.toString())
          //once the snack is gone or dismissed , notify the Snackbar Manager
          snackbarManager.setMessageShown(message.id)
        }
      }
    }
  }
}

@Composable
@ReadOnlyComposable
private fun resources(): Resources {
  LocalConfiguration.current
  return LocalContext.current.resources
}