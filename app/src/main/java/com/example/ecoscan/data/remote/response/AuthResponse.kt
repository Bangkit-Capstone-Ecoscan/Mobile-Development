package com.example.ecoscan.data.remote.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("token")
	val token: String
)

data class User(

	@field:SerializedName("firstName")
	val firstName: String,

	@field:SerializedName("lastName")
	val lastName: String,

	@field:SerializedName("username")
	val username: String
)
