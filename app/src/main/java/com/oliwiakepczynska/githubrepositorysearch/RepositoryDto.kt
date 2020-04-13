package com.oliwiakepczynska.githubrepositorysearch

import com.oliwiakepczynska.githubrepositorysearch.repository.Repository

data class RepositorySearchDto(
    val total_count: Int?,
    val incomplete_results: Boolean?,
    val items: List<Repository>?)

data class CommitsDto(val total: Int?)

data class BranchDto(val name: String?)