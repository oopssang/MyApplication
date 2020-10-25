package com.oopssang.book.data

import com.google.gson.annotations.SerializedName

data class SearchBookResponse(
    @SerializedName("SearchMeta")
    val SearchMeta: SearchMeta? = null,
    @SerializedName("BookResult")
    val BookResult: String? = null,
    @SerializedName("resultCode")
    val resultCode: String? = null,
    @SerializedName("BookResult")
    val tid: String? = null
)

data class BookResult(
    @SerializedName("total_count")
    val total_count: Int? = null,
    @SerializedName("pageable_count")
    val pageable_count: Int? = null,
    @SerializedName("is_end")
    val is_end: Boolean? = null,
    @SerializedName("documents")
    val documents: BookResult? = null
)