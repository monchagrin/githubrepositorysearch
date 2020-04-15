package com.oliwiakepczynska.githubrepositorysearch.domain.entity

import android.os.Parcel
import android.os.Parcelable
import com.oliwiakepczynska.githubrepositorysearch.repository.RepositoryProprietor

data class Repository(
    val id: Int?,
    val name: String?,
    val full_name: String?,
    val owner: RepositoryProprietor?,
    val private: Boolean,
    val url: String?,
    val description: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(RepositoryProprietor::class.java.classLoader),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(full_name)
        parcel.writeParcelable(owner, flags)
        parcel.writeByte(if (private) 1 else 0)
        parcel.writeString(url)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Repository> {
        override fun createFromParcel(parcel: Parcel): Repository {
            return Repository(
                parcel
            )
        }

        override fun newArray(size: Int): Array<Repository?> {
            return arrayOfNulls(size)
        }
    }
}