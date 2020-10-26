package com.oopssang.book.data

import Documents
import Meta
import com.google.gson.annotations.SerializedName

data class SearchBookResponse(
    @SerializedName("Documents")
    val documents : List<Documents>,
    @SerializedName("Meta")
    val meta : Meta
)
