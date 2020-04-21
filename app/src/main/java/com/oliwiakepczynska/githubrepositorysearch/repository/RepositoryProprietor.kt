package com.oliwiakepczynska.githubrepositorysearch.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class RepositoryProprietor(
    val login: String?,
    val id: Int?,
    val avatar_url: String?
) : Parcelable