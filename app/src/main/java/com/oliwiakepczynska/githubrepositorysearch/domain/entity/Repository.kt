package com.oliwiakepczynska.githubrepositorysearch.domain.entity

import android.os.Parcelable
import com.oliwiakepczynska.githubrepositorysearch.repository.RepositoryProprietor
import kotlinx.android.parcel.Parcelize

@Parcelize
class Repository(
    val id: Int?,
    val name: String?,
    val owner: RepositoryProprietor?,
    val private: Boolean,
    val description: String?
) : Parcelable