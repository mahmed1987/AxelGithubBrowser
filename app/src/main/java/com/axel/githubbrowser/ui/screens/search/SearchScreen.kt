package com.axel.githubbrowser.ui.screens.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.axel.githubbrowser.R
import com.axel.githubbrowser.core.state.SnackbarManager
import com.axel.githubbrowser.core.structures.Failure
import com.axel.githubbrowser.models.view.ViewSearchResult
import com.axel.githubbrowser.ui.styles.*
import com.axel.githubbrowser.ui.theme.AxelGithubBrowserTheme
import com.axel.githubbrowser.ui.widgets.RemoteImage

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen() {
  val viewModel = hiltViewModel<SearchViewModel>()
  val searchUiState by viewModel.uiState.collectAsStateWithLifecycle()
  val loading by viewModel.loading.collectAsStateWithLifecycle()
  val failure by viewModel.failure.collectAsStateWithLifecycle()


  var currentQuery by remember { mutableStateOf("") }

  Surface(color = MaterialTheme.colorScheme.background) {
    Column() {
      SearchBar(query = currentQuery, onQueryChanged = {
        viewModel.executeIntention(SearchIntentions.SearchByName(it))
        currentQuery = it
      })
      AnimatedVisibility(visible = loading) {
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
      }
      SearchResultList(searchUiState) {
        viewModel.executeIntention(SearchIntentions.GetById(it))
      }
    }
  }

  /* Detail Sheet */
  searchUiState.selectedUser?.let {
    UserBottomSheet(user = it) {
      viewModel.executeIntention(SearchIntentions.ResetSelectedUser)
    }
  }

  /* Error Snackbar */
  LaunchedEffect(key1 = failure) {
    if (failure != Failure.Empty) {
      viewModel.resetError()
      SnackbarManager.showMessage(R.string.error_occurred)
    }
  }
}

@Composable
fun SearchResultList(searchUiState: SearchUiState, onUserClicked: (String) -> Unit) {
  Column(modifier = Modifier.fillMaxWidth()) {
    SmallSpacer()
    LazyColumn(contentPadding = PaddingValues(top = mediumUnit)) {
      items(searchUiState.viewSearchResults.users) { user ->
        SearchListItem(viewSearchResult = user, onUserClicked)
      }
    }
  }
}

@Composable
fun SearchBar(query: String, onQueryChanged: (String) -> Unit) {
  TextField(
    modifier = Modifier.fillMaxWidth(),
    placeholder = { LargeBody(text = "Enter Username") },
    value = query,
    onValueChange = onQueryChanged
  )
}

@Composable
fun SearchListItem(viewSearchResult: ViewSearchResult, onUserClicked: (String) -> Unit) {
  ListItem(
    modifier = Modifier.clickable { onUserClicked(viewSearchResult.name) },
    headlineContent = { LargeBody(text = viewSearchResult.name) },
    leadingContent = {
      RemoteImage(viewSearchResult.imageUrl)
    },
    supportingContent = {
      SmallBody(text = viewSearchResult.type)
    })
}

@Preview
@Composable
fun PreviewSearchResultList() {
  AxelGithubBrowserTheme() {
    SearchResultList(SearchUiState.placeholder()){
    }
  }

}
