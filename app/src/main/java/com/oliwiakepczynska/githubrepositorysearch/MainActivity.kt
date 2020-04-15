package com.oliwiakepczynska.githubrepositorysearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.oliwiakepczynska.githubrepositorysearch.repository.RepositoryDetails

class MainActivity : AppCompatActivity(), IErrorsView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentFragment()
        GenericErrorsPresenter(this, IErrorsInteractor.create(RepositoryDetails.errorsStream))
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
