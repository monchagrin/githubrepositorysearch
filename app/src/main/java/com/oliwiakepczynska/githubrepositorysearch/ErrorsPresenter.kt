package com.oliwiakepczynska.githubrepositorysearch

import io.reactivex.Observable

interface IErrorsInteractor {
    fun onNoInternetConnectionError(onError: (exception: Throwable) -> Unit)

    companion object {
        fun create(errorsStream: Observable<Throwable>): IErrorsInteractor {
            return ErrorsInteractor(errorsStream)
        }
    }
}

interface IErrorsView {
    fun onNoInternetConnectionError()
}

class GenericErrorsPresenter(view: IErrorsView, service: IErrorsInteractor) {
    init {
        service.onNoInternetConnectionError { view.onNoInternetConnectionError() }
    }
}