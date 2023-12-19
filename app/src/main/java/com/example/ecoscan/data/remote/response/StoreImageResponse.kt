package com.example.ecoscan.data.remote.response

import com.google.gson.annotations.SerializedName

data class StoreImageResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("url")
	val url: String,
)
