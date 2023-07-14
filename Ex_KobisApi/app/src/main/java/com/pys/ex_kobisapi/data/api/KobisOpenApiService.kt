package com.pys.ex_kobisapi.data.api

import com.pys.ex_kobisapi.data.model.MovieResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface KobisOpenApiService {

    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    suspend fun getMovieInfo(@Query("key") key : String, @Query("targetDt") targetDt : String): ApiResponse<MovieResponse>

}