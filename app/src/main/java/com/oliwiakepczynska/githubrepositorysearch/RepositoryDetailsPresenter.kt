package com.oliwiakepczynska.githubrepositorysearch

import com.oliwiakepczynska.githubrepositorysearch.repository.GithubUtils
import com.oliwiakepczynska.githubrepositorysearch.repository.Repository


interface IRepositoryDetailsService {

    fun getRepositoryDetails(user: String, repositoryName: String)
    fun onDetailsLoaded(func: (details: RepositoryDetailsDto) -> Unit)

    companion object {
        fun create() = RepositoryDetailsInteractor(GithubUtils.create())
        fun create(repository: GithubUtils) = RepositoryDetailsInteractor(repository)
    }
}

interface IRepositoryDetailsView {

    fun loadRepositoryDetails(func: (user: String, repository: String) -> Unit)
    fun repositoryInformationLoaded(details: RepositoryDetailsDto)

    companion object {
        fun create(repository: Repository) = RepositoryDetailsFragment.newInstance(repository)
    }
}

class RepositoryDetailsPresenter(view: IRepositoryDetailsView, service: IRepositoryDetailsService) {
    init {
        view.loadRepositoryDetails { user, repository ->
            service.getRepositoryDetails(
                user,
                repository
            )
        }
        service.onDetailsLoaded { view.repositoryInformationLoaded(it) }
    }
}
