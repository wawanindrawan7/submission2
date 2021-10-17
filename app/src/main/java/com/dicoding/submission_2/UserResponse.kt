package com.dicoding.submission_2

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("items")
	val userResponse: List<UserResponseItem>
)

data class UserResponseItem(


	@field:SerializedName("bio")
	val bio: String,

	@field:SerializedName("login")
	val login: String,


	@field:SerializedName("followers")
	val followers: Int,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("following")
	val following: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("company")
	val company: Any,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("public_repos")
	val publicRepos: Int,

	@field:SerializedName("email")
	val email: Any,

	@field:SerializedName("organizations_url")
	val organizationsUrl: String
)
