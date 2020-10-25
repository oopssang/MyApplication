package com.oopssang.book.data

import com.google.gson.annotations.SerializedName

data class SearchMeta(
    @SerializedName("total_count")
    val total_count: String? = null,
    @SerializedName("pageable_count")
    val pageable_count: String? = null,
    @SerializedName("is_end")
    val is_end: String? = null
)