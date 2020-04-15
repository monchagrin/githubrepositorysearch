package com.oliwiakepczynska.githubrepositorysearch.repository

import com.oliwiakepczynska.githubrepositorysearch.*
import com.oliwiakepczynska.githubrepositorysearch.domain.data.NetworkService
import com.oliwiakepczynska.githubrepositorysearch.domain.entity.Repository
import com.oliwiakepczynska.githubrepositorysearch.domain.entity.RepositoryDetails
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class RepositoryRemoteData(
    private val api: NetworkService,
    private val errorsStream: PublishSubject<Throwable>
) : RepositoryDetails {

    override fun getAllRepositories(): Observable<List<Repository>> =
        executeRequest(api.getAllRepositories(), emptyList())

    override fun searchRepository(query: String): Observable<RepositorySearchDto> =
        executeRequest(api.searchForRepositories(query), RepositorySearchDto(0, true, emptyList()))

    override fun getCommitsForRepository(
        user: String,
        repository: String
    ): Observable<List<CommitsDto>> =
        executeRequest(api.getCommitsForRepository(user, repository)
            .doOnNext { if (it.code() == 202) throw StatsCachingException() }
            .retryWhen {
                val maxRetries = 3
                var retryCount = 0
                val delaySeconds = 3L

                it.flatMap {
                    if (it is StatsCachingException) {
                        if (++retryCount < maxRetries) {
                            return@flatMap Observable.timer(delaySeconds, TimeUnit.SECONDS)
                        }
                    }
                    return@flatMap Observable.error<Throwable>(it)
                }
            }
            .map { it.body()!! }, emptyList()
        )

    override fun getBranchesForRepository(
        user: String,
        repository: String
    ): Observable<List<BranchDto>> =
        executeRequest(api.getBranchesForRepository(user, repository), emptyList())


    private fun <T> executeRequest(
        request: Observable<T>,
        returnOnError: T? = null
    ): Observable<T> {
        return request.onErrorReturn {
            errorsStream.onNext(it)
            returnOnError
        }
    }
}