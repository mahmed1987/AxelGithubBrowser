package com.axel.githubbrowser.ui.screens.search

import com.axel.githubbrowser.models.view.ViewSearchResult
import com.axel.githubbrowser.models.view.ViewSearchResults
import com.axel.githubbrowser.models.view.ViewUser

data class SearchUiState(
  val viewSearchResults: ViewSearchResults = ViewSearchResults(),
  val selectedUser: ViewUser? = null
) {
  companion object {
    fun placeholder() =
        SearchUiState(
          ViewSearchResults(
            totalCount = 2,
            users = listOf(
              ViewSearchResult(
                name = "JohnDoe",
                imageUrl = "http://example.com/johndoe.jpg",
                type = "User"
              ),
              ViewSearchResult(
                name = "JaneDoe",
                imageUrl = "http://example.com/janedoe.jpg",
                type = "User"
              )
            )
          )
        )
  }
}