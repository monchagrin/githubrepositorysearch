package com.oliwiakepczynska.githubrepositorysearch.domain.entity

import com.oliwiakepczynska.githubrepositorysearch.domain.data.NetworkClient
import com.oliwiakepczynska.githubrepositorysearch.domain.data.NetworkService
import com.oliwiakepczynska.githubrepositorysearch.repository.RepositoryRemoteData
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface RepositoryDetails {
    fun getAllRepositories(): Observable<List<Repository>>
    fun searchRepository(query: String): Observable<RepositorySearchDto>
    fun getCommitsForRepository(user: String, repository: String): Observable<List<CommitsDto>>
    fun getBranchesForRepository(user: String, repository: String): Observable<List<BranchDto>>

    companion object {
        fun create(): RepositoryDetails {
            return RepositoryRemoteData(
                NetworkClient.create(),
                errorsStream
            )
        }

        fun create(api: NetworkService, errorsStream: PublishSubject<Throwable>): RepositoryDetails {
            return RepositoryRemoteData(
                api,
                errorsStream
            )
        }

        val errorsStream: PublishSubject<Throwable> = PublishSubject.create()
    }
}
