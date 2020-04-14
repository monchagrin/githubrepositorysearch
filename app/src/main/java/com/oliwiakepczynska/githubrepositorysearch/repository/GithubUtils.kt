package com.oliwiakepczynska.githubrepositorysearch.repository

import com.oliwiakepczynska.githubrepositorysearch.*
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface GithubRepository {
    fun findAllRepositories(): Observable<List<Repository>>
    fun searchRepositories(query: String): Observable<RepositorySearchDto>
    fun getCommitsForRepository(user: String, repository: String): Observable<List<CommitsDto>>
    fun getBranchesForRepository(user: String, repository: String): Observable<List<BranchDto>>

    companion object {
        fun create(): GithubRepository {
            return GithubUtils(NetworkClient.create(), errorsStream)
        }

        fun create(api: NetworkService, errorsStream: PublishSubject<Throwable>): GithubRepository {
            return GithubUtils(api, errorsStream)
        }

        val errorsStream: PublishSubject<Throwable> = PublishSubject.create()
    }
}
