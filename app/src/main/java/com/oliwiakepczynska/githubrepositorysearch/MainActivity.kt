package com.oliwiakepczynska.githubrepositorysearch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oliwiakepczynska.githubrepositorysearch.repository.GithubUtils

class MainActivity : AppCompatActivity(), IErrorsView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentFragment()
        GenericErrorsPresenter(this, IErrorsInteractor.create(GithubUtils.errorsStream))
    }

    private fun setContentFragment() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, IRepositoryListView.create())
            commit()
        }
    }

    override fun onNoInternetConnectionError() {
        Toast.makeText(
            this@MainActivity,
            "No Internet connection.", Toast.LENGTH_LONG
        ).show();
    }
}
