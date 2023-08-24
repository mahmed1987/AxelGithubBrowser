package com.axel.githubbrowser.ui.screens.search

import com.axel.githubbrowser.core.structures.Either
import com.axel.githubbrowser.core.structures.Failure
import com.axel.githubbrowser.data.sources.users.UsersDataSource
import com.axel.githubbrowser.models.view.ViewSearchResults
import com.axel.githubbrowser.models.view.ViewUser

class FakeRepository(
  private val returnFailures: Boolean = false,
  private val totalCount: Int = 10
) : UsersDataSource {
  override suspend fun fetchUsers(search: String): Either<Failure, ViewSearchResults> {
    if (returnFailures)
      return Either.Left(Failure.AuthError)
    return Either.Right(ViewSearchResults(totalCount))
  }

  override suspend fun getUserById(id: String): Either<Failure, ViewUser> {
    if (returnFailures)
      return Either.Left(Failure.AuthError)
    return Either.Right(ViewUser.placeholder())
  }
}