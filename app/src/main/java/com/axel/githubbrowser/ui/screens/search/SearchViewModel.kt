package com.axel.githubbrowser.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axel.githubbrowser.business.user.GetUserById
import com.axel.githubbrowser.business.user.SearchFilter
import com.axel.githubbrowser.business.user.SearchUsers
import com.axel.githubbrowser.core.extensions.toRight
import com.axel.githubbrowser.core.structures.Either
import com.axel.githubbrowser.core.structures.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
  private val search: SearchUsers,
  private val getUserById: GetUserById
) : ViewModel() {

  /* The UiState for our screen*/
  private val _uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState())
  val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

  /* The Loading state of our screen*/
  private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
  val loading: StateFlow<Boolean> = _loading.asStateFlow()

  /* The failure state of our screen . */
  private val _failure: MutableStateFlow<Failure> = MutableStateFlow(Failure.Empty)
  val failure: StateFlow<Failure> = _failure.asStateFlow()

  /** The intent (part of a rudimentary version of MVI). Note that
   *  it is a MutableSharedFlow owing to the fact that we would issue similar intentions,
   *  multiple times and would expect them to be resolved into state.
   . */
  private val intent = MutableSharedFlow<SearchIntentions>(replay = 1)

  init {
    viewModelScope.launch {
      intent.debounce { it.debounceMs }.collect {
        _loading.emit(true)
          reduce(_uiState.value, it).either({
            _failure.value = it
          }, { searchUiState ->
            _uiState.value = searchUiState
          })
          _loading.value = false
      }
    }
  }

  /**
   * The single point of contact between the View , and its State mutation logic
   * */
  fun executeIntention(intention: SearchIntentions) {
    viewModelScope.launch {
      intent.emit(intention)
    }
  }

  /**
   * The reducer , which takes in the current state , and returns a new state
   * */
  private suspend fun reduce(
    uiState: SearchUiState,
    intention: SearchIntentions
  ): Either<Failure, SearchUiState> {
    return when (intention) {
      is SearchIntentions.SearchByName -> search(uiState, SearchFilter.Name, intention.query)
      is SearchIntentions.GetById -> getUserById(uiState, intention.id)
      SearchIntentions.ResetSelectedUser -> uiState.copy(selectedUser = null).toRight()
    }
  }

  /**
   * When an error is displayed on the UI , we have to reset it to its initial state after it has been
   * displayed. If we do not reset its state , and a similar error arrives , the flow will not emit.
   *
   * Note: This issue cannot be solved via MutableSharedFlow .
   *
   * Read this https://developer.android.com/topic/architecture/ui-layer/events#consuming-trigger-updates
   * */
  suspend fun resetError() {
    _failure.emit(Failure.Empty)
  }
}


