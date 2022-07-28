package com.example.schoters.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetAllNewsByKeywordResponse(
    val articles: List<Article>?,
    val status: String?,
    val totalResults: Int?
) : Parcelable