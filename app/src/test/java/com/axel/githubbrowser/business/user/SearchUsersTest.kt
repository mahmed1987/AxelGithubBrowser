package com.axel.githubbrowser.business.user

import com.axel.githubbrowser.core.structures.Either
import com.axel.githubbrowser.models.view.ViewSearchResults
import com.axel.githubbrowser.ui.screens.search.FakeRepository
import com.axel.githubbrowser.ui.screens.search.SearchUiState
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SearchUsersTest {
  private lateinit var searchUsers: SearchUsers


  @Test
  fun `Failure results in EitherLeft`() = runBlocking {
    searchUsers = SearchUsers(FakeRepository(true))
    val result = searchUsers.invoke(SearchUiState(), query = "test")
    Truth.assertThat(result).isInstanceOf(Either.Left::class.java)
  }

  @Test
  fun `State is mutated , but a new one is not created`() = runBlocking {
    searchUsers = SearchUsers(FakeRepository(false))
    val initialState = SearchUiState(ViewSearchResults())
    val result = searchUsers.invoke(initialState, query = "test")
    val finalState = (result as Either.Right).b
    Truth.assertThat(initialState).isNotSameInstanceAs(finalState)
    Truth.assertThat(finalState.viewSearchResults.totalCount).isEqualTo(10)
  }



}