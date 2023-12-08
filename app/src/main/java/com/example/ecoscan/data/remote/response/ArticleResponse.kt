package com.example.ecoscan.data.remote.response

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

	@field:SerializedName("ArticlesResponse")
	val articlesResponse: List<ArticleResponseItem>
)

data class ArticleResponseItem(

	@field:SerializedName("imgUrl")
	val imgUrl: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("author-year")
	val authorYear: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("articleUrl")
	val articleUrl: String,

	@field:SerializedName("desc")
	val desc: List<String>
)
