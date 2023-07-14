package com.pys.ex_kobisapi.data.model

import com.google.gson.annotations.SerializedName

data class BoxOfficeResult(
    @SerializedName("dailyBoxOfficeList")
    var dailyBoxOfficeList: List<MovieInfo> = arrayListOf()
)
