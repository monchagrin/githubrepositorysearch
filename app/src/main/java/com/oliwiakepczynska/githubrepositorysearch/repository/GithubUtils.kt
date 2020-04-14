package com.oliwiakepczynska.githubrepositorysearch.repository

import com.oliwiakepczynska.githubrepositorysearch.*
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface GithubUtils {
    fun findAllRepositories(): Observable<List<Repository>>
    fun searchRepositories(query: String): Observable<RepositorySearchDto>
    fun getCommitsForRepository(user: String, repository: String): Observable<List<CommitsDto>>
    fun getBranchesForRepository(user: String, repository: String): Observable<List<BranchDto>>

    companion object {
        fun create(): GithubUtils {
            return GithubRepository(NetworkClient.create(), errorsStream)
        }

        fun create(api: NetworkService, errorsStream: PublishSubject<Throwable>): GithubUtils {
            return GithubRepository(api, errorsStream)
        }

        val errorsStream: PublishSubject<Throwable> = PublishSubject.create()
    }
}
