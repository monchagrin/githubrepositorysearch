package com.oliwiakepczynska.githubrepositorysearch

import io.reactivex.Observable
import java.net.UnknownHostException

class NoInternetConnectionException : Throwable()

class ErrorsInteractor(private val errorsStream: Observable<Throwable>) : IErrorsInteractor {

    override fun onNoInternetConnectionError(onError: (exception: Throwable) -> Unit) {
        errorsStream
                .filter { it is UnknownHostException }
                .map { NoInternetConnectionException() }
                .subscribe { onError(it) }
    }
}