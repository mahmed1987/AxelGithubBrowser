package com.axel.githubbrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.axel.githubbrowser.business.user.SearchUsers
import com.axel.githubbrowser.core.state.rememberAppState
import com.axel.githubbrowser.ui.main.MainApp
import com.axel.githubbrowser.ui.theme.AxelGithubBrowserTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  @Inject
  lateinit var search: SearchUsers
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    /* Go full screen */
    setContent {
      AxelGithubBrowserTheme() {
        val appState = rememberAppState()
        MainApp(appState = appState)
      }
    }
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  AxelGithubBrowserTheme() {
    Greeting("Android")
  }
}