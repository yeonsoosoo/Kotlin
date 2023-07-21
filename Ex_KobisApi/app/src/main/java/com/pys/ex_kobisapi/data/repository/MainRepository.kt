package com.pys.ex_kobisapi.data.repository

import android.content.Context
import com.pys.ex_kobisapi.data.api.KobisOpenApiClient
import com.pys.ex_kobisapi.data.model.MovieResponse
import com.skydoves.sandwich.ApiResponse

class MainRepository(private val context : Context) {

    // 영화 정보 API
    suspend fun getMovieInfo(key : String, targetDt : String) : ApiResponse<MovieResponse> = KobisOpenApiClient.kobisOpenApiService.getMovieInfo(key, targetDt)
}