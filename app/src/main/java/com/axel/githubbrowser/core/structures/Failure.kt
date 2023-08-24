package com.axel.githubbrowser.core.structures

import androidx.compose.runtime.Immutable

@Immutable
sealed class Failure {
  object NetworkConnection : Failure()
  object ServerError : Failure()
  object AuthError : Failure()
  object Forbidden : Failure()
  object BadRequest : Failure()
  object NotFound : Failure()
  object UnSupportedMediaType : Failure()
  object InternalServerError : Failure()
  object Empty :Failure()

  /** * Extend this class for feature specific failures.*/
  abstract class FeatureFailure : Failure()
}


