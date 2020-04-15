package com.oliwiakepczynska.githubrepositorysearch

import com.oliwiakepczynska.githubrepositorysearch.repository.RepositoryDetails
import com.oliwiakepczynska.githubrepositorysearch.repository.Repository

interface IRepositoryListView {
    fun loadItems(func: () -> Unit)
    fun searchItems(func: (query: String) -> Unit)
    fun itemsLoaded(items: List<Repository>)

    companion object {
        fun create() = RepositoryListFragment.newInstance()
    }
}

interface IRepositoryListService {
    fun findAll()
    fun search(query: String)
    fun onReposFound(func: (List<Repository>) -> Unit)

    companion object {
        fun create() = RepositoryListInteractor(RepositoryDetails.create())
        fun create(repository: RepositoryDetails) = RepositoryListInteractor(repository)
    }
}

class RepositoryListPresenter(view: IRepositoryListView, service: IRepositoryListService) {

    init {
        view.loadItems {
            service.findAll()
        }

        view.searchItems {
            service.search(it)
        }

        service.onReposFound {
            view.itemsLoaded(it)
        }
    }
}