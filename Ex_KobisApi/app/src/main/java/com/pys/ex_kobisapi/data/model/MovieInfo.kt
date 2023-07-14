package com.pys.ex_kobisapi.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieInfo (
    @SerializedName("movieNm")
    var movieNm: String?,
    @SerializedName("rankInten")
    var rankInten: String?,
    @SerializedName("rank")
    var rank: String?
    ) : Serializable