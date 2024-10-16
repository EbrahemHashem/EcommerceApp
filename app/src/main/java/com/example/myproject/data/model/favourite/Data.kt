package com.example.myproject.data.model.favourite


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("current_page")
    val currentPage: Int? = 0,
    @SerializedName("data")
    val `data`: List<DataX>? = listOf(),
    @SerializedName("first_page_url")
    val firstPageUrl: String? = "",
    @SerializedName("from")
    val from: Int? = 0,
    @SerializedName("last_page")
    val lastPage: Int? = 0,
    @SerializedName("last_page_url")
    val lastPageUrl: String? = "",
    @SerializedName("next_page_url")
    val nextPageUrl: Any? = Any(),
    @SerializedName("path")
    val path: String? = "",
    @SerializedName("per_page")
    val perPage: Int? = 0,
    @SerializedName("prev_page_url")
    val prevPageUrl: Any? = Any(),
    @SerializedName("to")
    val to: Int? = 0,
    @SerializedName("total")
    val total: Int? = 0
)