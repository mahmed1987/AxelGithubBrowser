package com.axel.githubbrowser.ui.screens.search

import com.axel.githubbrowser.business.user.GetUserById
import com.axel.githubbrowser.business.user.SearchUsers
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class SearchViewModelTest {

  private val testDispatcher = StandardTestDispatcher()
  private lateinit var viewModel: SearchViewModel

  private val searchUserUseCase = SearchUsers(FakeRepository())
  private val getUserByIdUseCase = Mockito.mock(GetUserById::class.java)

  @Before
  fun setup() {
    Dispatchers.setMain(testDispatcher)
    viewModel = SearchViewModel(searchUserUseCase, getUserByIdUseCase)
  }

  @Test
  fun `When intention is executed , state is changed`() = runBlocking {
    viewModel.executeIntention(SearchIntentions.SearchByName("testing"))
    testDispatcher.scheduler.advanceUntilIdle()
    val uiState = viewModel.uiState.value
    Truth.assertThat(uiState.viewSearchResults.totalCount).isEqualTo(10)
  }


}

