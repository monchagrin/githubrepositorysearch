package com.oliwiakepczynska.githubrepositorysearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.oliwiakepczynska.githubrepositorysearch.domain.error.GenericErrorsPresenter
import com.oliwiakepczynska.githubrepositorysearch.domain.error.IErrorsInteractor
import com.oliwiakepczynska.githubrepositorysearch.domain.error.IErrorsView
import com.oliwiakepczynska.githubrepositorysearch.domain.entity.RepositoryDetails
import com.oliwiakepczynska.githubrepositorysearch.presenter.list.IRepositoryListView

class MainActivity : AppCompatActivity(),
    IErrorsView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentFragment()
        GenericErrorsPresenter(
            this,
            IErrorsInteractor.create(
                RepositoryDetails.errorsStream
            )
        )
    }

    private fun setContentFragment() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, IRepositoryListView.create())
            commit()
        }
    }

    override fun onNoInternetConnectionError() {
        Snackbar.make(findViewById(android.R.id.content), "No internet connection", Snackbar.LENGTH_SHORT).show()
    }
}
