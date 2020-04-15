package com.oliwiakepczynska.githubrepositorysearch

import androidx.fragment.app.FragmentManager
import com.oliwiakepczynska.githubrepositorysearch.domain.entity.Repository

object FragmentNavigator {

    fun openRepositoryDetails(fragmentManager: FragmentManager, repository: Repository) {
        fragmentManager.beginTransaction().apply {
            replace(R.id.container, IRepositoryDetailsView.create(repository))
            addToBackStack("RepositoryDetails")
            commit()
        }
    }
}