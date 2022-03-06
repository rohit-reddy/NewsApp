
package com.rohith.rohithltiassessment.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://bruce-v2-mob.fairfaxmedia.com.au/1/coding_test/13ZZQX/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NewsApiService {
    @GET("full")
    suspend fun getNewsArticle():
            NewsResponse
}

object NewsApi {
    val RETROFIT_SERVICE : NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java) }
}


