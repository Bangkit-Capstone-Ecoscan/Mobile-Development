package com.example.ecoscan.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(

	@field:SerializedName("DetailResponse")
	val detailResponse: List<DetailResponseItem>
)

data class DetailResponseItem(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("id")
	val id: String
)

data class Data(

	@field:SerializedName("imgUrl")
	val imgUrl: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("author-year")
	val authorYear: String,

	@field:SerializedName("articleUrl")
	val articleUrl: String,

	@field:SerializedName("desc")
	val desc: List<String>
)
