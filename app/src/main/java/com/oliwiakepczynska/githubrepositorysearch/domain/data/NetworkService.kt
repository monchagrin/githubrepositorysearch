package com.oliwiakepczynska.githubrepositorysearch.domain.data

import com.oliwiakepczynska.githubrepositorysearch.BranchDto
import com.oliwiakepczynska.githubrepositorysearch.CommitsDto
import com.oliwiakepczynska.githubrepositorysearch.RepositorySearchDto
import com.oliwiakepczynska.githubrepositorysearch.domain.entity.Repository
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("/repositories")
    fun getAllRepositories(): Observable<List<Repository>>

    @GET("/search/repositories")
    fun searchForRepositories(@Query("q") query: String): Observable<RepositorySearchDto>

    @GET("/repos/{owner}/{repository}/stats/commit_activity")
    fun getCommitsForRepository(@Path("owner") user: String, @Path("repository") repository: String): Observable<Response<List<CommitsDto>>>

    @GET("/repos/{owner}/{repository}/branches")
    fun getBranchesForRepository(@Path("owner") user: String, @Path("repository") repository: String): Observable<List<BranchDto>>

}