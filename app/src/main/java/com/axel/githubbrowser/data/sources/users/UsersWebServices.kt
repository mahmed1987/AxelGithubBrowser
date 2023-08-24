package com.axel.githubbrowser.data.sources.users

import com.axel.githubbrowser.core.structures.Either
import com.axel.githubbrowser.core.structures.Failure
import com.axel.githubbrowser.models.network.GithubResponse
import com.axel.githubbrowser.models.network.NetworkUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UsersWebServices {
  @GET("search/users")
  suspend fun search(@Query("q") query: String): Response<GithubResponse>

  @GET("users/{id}")
  suspend fun getUserById(@Path("id") id: String): Response<NetworkUser>
}