package com.pys.ex_kobisapi.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("boxOfficeResult")
    var boxOfficeResult : BoxOfficeResult?

)
