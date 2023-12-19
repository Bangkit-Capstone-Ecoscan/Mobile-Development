package com.example.ecoscan.data.remote.response

import com.google.gson.annotations.SerializedName

data class PaketResponse(

	@field:SerializedName("user")
	val user: UserPaket,

	@field:SerializedName("token")
	val token: String
)

data class UserPaket(

	@field:SerializedName("firstName")
	val firstName: String,

	@field:SerializedName("lastName")
	val lastName: String,

	@field:SerializedName("quota")
	val quota: Int,

	@field:SerializedName("username")
	val username: String
)
