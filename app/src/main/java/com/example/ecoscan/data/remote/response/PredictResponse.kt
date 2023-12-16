package com.example.ecoscan.data.remote.response

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("carbohydrates")
	val carbohydrates: String? = null,

	@field:SerializedName("emission")
	val emission: String? = null,

	@field:SerializedName("calcium")
	val calcium: String? = null,

	@field:SerializedName("vitamins")
	val vitamins: String? = null,

	@field:SerializedName("food-name")
	val foodName: String? = null,

	@field:SerializedName("protein")
	val protein: String? = null,

	@field:SerializedName("fat")
	val fat: String? = null
)
