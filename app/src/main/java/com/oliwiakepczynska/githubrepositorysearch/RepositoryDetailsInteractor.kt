package com.oliwiakepczynska.githubrepositorysearch


import com.oliwiakepczynska.githubrepositorysearch.repository.RepositoryDetails
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RepositoryDetailsInteractor(private val repository: RepositoryDetails) : IRepositoryDetailsService {

    private val commitsCountSubject = PublishSubject.create<Int>()
    private val branchesCountSubject = PublishSubject.create<Int>()
    private val repositoryDetailsPublishSubject = PublishSubject.create<RepositoryDetailsDto>()

    override fun getRepositoryDetails(user: String, repositoryName: String) {
        getCommitsInternal(user, repositoryName)
        getBranchesInternal(user, repositoryName)
        combineRepositoryDetailsInternal()
    }

    private fun getCommitsInternal(user: String, repositoryName: String) {
        repository
            .getCommitsForRepository(user, repositoryName)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapIterable { it }
            .reduce(0, { acc, next -> acc + next.total!! })
            .subscribe { count -> commitsCountSubject.onNext(count!!) }
    }

    private fun getBranchesInternal(user: String, repositoryName: String) {
        repository
            .getBranchesForRepository(user, repositoryName)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { branchesCountSubject.onNext(it.count()) }
    }

    private fun combineRepositoryDetailsInternal() {
        Observables
            .combineLatest(commitsCountSubject, branchesCountSubject, { commits, branches ->
                RepositoryDetailsDto(commits, branches)
            })
            .subscribe { repositoryDetailsPublishSubject.onNext(it) }
    }

    override fun onDetailsLoaded(func: (details: RepositoryDetailsDto) -> Unit) {
        repositoryDetailsPublishSubject.subscribe { func(it) }
    }
}