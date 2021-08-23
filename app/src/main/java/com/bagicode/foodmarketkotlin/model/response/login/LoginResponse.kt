package com.bagicode.foodmarketkotlin.model.response.login


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @Expose
    @SerializedName("token")
    val token: String,
    @Expose
    @SerializedName("success")
    val success: Boolean,
    @Expose
    @SerializedName("user")
    val user: User
)