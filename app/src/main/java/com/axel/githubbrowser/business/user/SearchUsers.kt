package com.axel.githubbrowser.business.user

import com.axel.githubbrowser.core.structures.Either
import com.axel.githubbrowser.core.structures.Failure
import com.axel.githubbrowser.core.structures.map
import com.axel.githubbrowser.data.sources.users.UsersDataSource
import com.axel.githubbrowser.ui.screens.search.SearchUiState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchUsers @Inject constructor(private val usersDataSource: UsersDataSource) {
  suspend operator fun invoke(
    currentUiState: SearchUiState,
    filter: SearchFilter = SearchFilter.Name,
    query: String
  ): Either<Failure, SearchUiState> {
    return usersDataSource.fetchUsers(query).map { currentUiState.copy(viewSearchResults = it) }
  }
}

enum class SearchFilter {
  Name,
  Age,
  Class,
  Nationality
}