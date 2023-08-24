package com.axel.githubbrowser.core.extensions

import com.axel.githubbrowser.core.structures.Either
import com.axel.githubbrowser.core.structures.Failure

inline fun <reified T > T.toRight(): Either<Failure, T> {
  return Either.Right(this)
}