package com.axel.githubbrowser.models.view

data class ViewUser(
  val id: Long,
  val login: String,
  val avatarUrl: String,
  val followers: Int,
  val publicRepos: Int
){
  companion object {
    fun placeholder()  = ViewUser(
      id = 12345L,
      login = "johnDoe",
      avatarUrl = "https://example.com/avatar/johnDoe.png",
      followers = 100,
      publicRepos = 50
    )
  }

}