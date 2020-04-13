package com.oliwiakepczynska.githubrepositorysearch.repository

import android.os.Parcel
import android.os.Parcelable

data class RepositoryOwner(
    val login: String?,
    val id: Int?,
    val avatar_url: String?,
    val url: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeValue(id)
        parcel.writeString(avatar_url)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepositoryOwner> {
        override fun createFromParcel(parcel: Parcel): RepositoryOwner {
            return RepositoryOwner(parcel)
        }

        override fun newArray(size: Int): Array<RepositoryOwner?> {
            return arrayOfNulls(size)
        }
    }
}