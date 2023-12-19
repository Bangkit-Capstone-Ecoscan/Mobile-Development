package com.example.ecoscan.data.remote.response

import com.google.gson.annotations.SerializedName

data class StoreResultResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String
)

data class Data(

	@field:SerializedName("carbohydrates")
	val carbohydrates: String,

	@field:SerializedName("food_name")
	val foodName: String,

	@field:SerializedName("emission")
	val emission: String,

	@field:SerializedName("calcium")
	val calcium: String,

	@field:SerializedName("vitamins")
	val vitamins: String,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("protein")
	val protein: String,

	@field:SerializedName("fat")
	val fat: String,

	@field:SerializedName("userId")
	val userId: String
)
