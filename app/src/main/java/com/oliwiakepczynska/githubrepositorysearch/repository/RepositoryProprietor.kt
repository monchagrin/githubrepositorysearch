package com.oliwiakepczynska.githubrepositorysearch.repository

import android.os.Parcel
import android.os.Parcelable

data class RepositoryProprietor(
    val login: String?,
    val id: Int?,
    val avatar_url: String?,
    val url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeValue(id)
        parcel.writeString(avatar_url)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepositoryProprietor> {
        override fun createFromParcel(parcel: Parcel): RepositoryProprietor {
            return RepositoryProprietor(parcel)
        }

        override fun newArray(size: Int): Array<RepositoryProprietor?> {
            return arrayOfNulls(size)
        }
    }
}