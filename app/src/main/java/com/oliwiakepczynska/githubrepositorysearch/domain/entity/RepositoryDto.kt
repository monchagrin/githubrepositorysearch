package com.oliwiakepczynska.githubrepositorysearch.domain.entity

import com.oliwiakepczynska.githubrepositorysearch.domain.entity.Repository

data class RepositorySearchDto(
    val total_count: Int?,
    val incomplete_results: Boolean?,
    val items: List<Repository>?)

data class CommitsDto(val total: Int?)

data class BranchDto(val name: String?)