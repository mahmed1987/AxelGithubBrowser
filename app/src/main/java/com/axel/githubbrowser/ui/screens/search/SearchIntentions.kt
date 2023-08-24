package com.axel.githubbrowser.ui.screens.search


sealed class SearchIntentions(val debounceMs: Long = 0L) {
  class SearchByName(val query: String) : SearchIntentions(300L)
  class GetById(val id: String) : SearchIntentions()
  object ResetSelectedUser : SearchIntentions()
}