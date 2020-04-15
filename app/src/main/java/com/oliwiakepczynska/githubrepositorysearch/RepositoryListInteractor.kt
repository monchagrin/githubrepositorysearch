package com.oliwiakepczynska.githubrepositorysearch

import com.oliwiakepczynska.githubrepositorysearch.repository.GithubUtils
import com.oliwiakepczynska.githubrepositorysearch.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RepositoryListInteractor(private val repository: GithubUtils) : IRepositoryListService {

    private val reposFoundSubject: PublishSubject<List<Repository>> = PublishSubject.create()

    override fun findAll() {
        repository
            .getAllRepositories()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                reposFoundSubject.onNext(it)
            }

    }

    override fun search(query: String) {
        repository.searchRepository(query)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.items != null }
            .map {
                it.items!!
            }
            .subscribe {
                reposFoundSubject.onNext(it)
            }
    }

    override fun onReposFound(func: (repositories: List<Repository>) -> Unit) {
        reposFoundSubject.subscribe {
            func(it)
        }
    }
}