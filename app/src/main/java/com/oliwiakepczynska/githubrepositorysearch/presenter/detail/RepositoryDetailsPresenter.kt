package com.oliwiakepczynska.githubrepositorysearch.presenter.detail

import com.oliwiakepczynska.githubrepositorysearch.domain.entity.RepositoryDetails
import com.oliwiakepczynska.githubrepositorysearch.domain.entity.Repository

interface IRepositoryDetailsService {

    fun getRepositoryDetails(user: String, repositoryName: String)
    fun onDetailsLoaded(func: (details: RepositoryDetailsDto) -> Unit)

    companion object {
        fun create() =
            RepositoryDetailsInteractor(
                RepositoryDetails.create()
            )

        fun create(repository: RepositoryDetails) =
            RepositoryDetailsInteractor(
                repository
            )
    }
}

interface IRepositoryDetailsView {

    fun loadRepositoryDetails(func: (user: String, repository: String) -> Unit)
    fun repositoryInformationLoaded(details: RepositoryDetailsDto)

    companion object {
        fun create(repository: Repository) =
            RepositoryDetailsFragment.newInstance(
                repository
            )
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
