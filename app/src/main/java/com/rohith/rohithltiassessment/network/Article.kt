
package com.rohith.rohithltiassessment.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsResponse(
    @Json(name = "assets") val sources: List<Source>
) : Parcelable

@Parcelize
data class Source (
    @Json(name = "id") val id: Long,
    @Json(name = "url") val url: String,
    @Json(name = "headline") val headline: String,
    @Json(name = "theAbstract") val theAbstract: String,
    @Json(name = "byLine") val byLine: String,
    @Json(name = "timeStamp") val timeStamp: Long,
    @Json(name = "relatedImages") val relatedImages: List<SourceImage>
) : Parcelable

@Parcelize
data class SourceImage (
    @Json(name = "url") val url: String
) : Parcelable


