package com.example.ecoscan.data.remote.response

import com.google.gson.annotations.SerializedName

data class ScanResultResponse(

	@field:SerializedName("modelResponse")
	val modelResponse: ModelResponse,

	@field:SerializedName("user")
	val user: Users,

	@field:SerializedName("token")
	val token: String
)

data class Users(

	@field:SerializedName("firstName")
	val firstName: String,

	@field:SerializedName("lastName")
	val lastName: String,

	@field:SerializedName("quota")
	val quota: Int,

	@field:SerializedName("username")
	val username: String
)

data class ModelResponse(

	@field:SerializedName("carbohydrates")
	val carbohydrates: String,

	@field:SerializedName("emission")
	val emission: String,

	@field:SerializedName("calcium")
	val calcium: String,

	@field:SerializedName("vitamins")
	val vitamins: String,

	@field:SerializedName("food-name")
	val foodName: String,

	@field:SerializedName("protein")
	val protein: String,

	@field:SerializedName("fat")
	val fat: String
)
