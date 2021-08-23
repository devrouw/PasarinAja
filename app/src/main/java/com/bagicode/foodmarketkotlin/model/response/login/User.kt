package com.bagicode.foodmarketkotlin.model.response.login


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose
    @SerializedName("id")
    val id: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("password")
    val password: Any
)