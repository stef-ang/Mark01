package com.stef_ang.mark01.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeMovie(
    val title: String?,
    val overview: String?,
    val image: String?,
    val releaseDate: String?,
    val rating: Double?
) : Parcelable