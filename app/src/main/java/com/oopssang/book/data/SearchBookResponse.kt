package com.oopssang.book.data

import Documents
import Meta
import com.google.gson.annotations.SerializedName

data class SearchBookResponse(
    @SerializedName("documents")
    val documents : List<Documents>,
    @SerializedName("meta")
    val meta : Meta
)
