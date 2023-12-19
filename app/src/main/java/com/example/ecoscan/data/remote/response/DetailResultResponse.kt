package com.example.ecoscan.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailResultResponse(

	@field:SerializedName("carbohydrates")
	val carbohydrates: String? = null,

	@field:SerializedName("food_name")
	val foodName: String? = null,

	@field:SerializedName("emission")
	val emission: String? = null,

	@field:SerializedName("calcium")
	val calcium: String? = null,

	@field:SerializedName("vitamins")
	val vitamins: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("protein")
	val protein: String? = null,

	@field:SerializedName("fat")
	val fat: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null
)
