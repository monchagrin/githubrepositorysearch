package com.oliwiakepczynska.githubrepositorysearch

import androidx.fragment.app.FragmentManager
import com.oliwiakepczynska.githubrepositorysearch.repository.Repository

object FragmentNavigator {

    fun openRepositoryDetails(fragmentManager: FragmentManager, repository: Repository) {
        fragmentManager.beginTransaction().apply {
            replace(R.id.container, IRepositoryDetailsView.create(repository))
            addToBackStack("RepositoryDetails")
            commit()
        }
    }
}