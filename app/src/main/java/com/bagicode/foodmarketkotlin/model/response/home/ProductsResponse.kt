package com.bagicode.foodmarketkotlin.model.response.home

import com.bagicode.foodmarketkotlin.model.response.login.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @Expose
    @SerializedName("message")
    val message: String,
    @Expose
    @SerializedName("success")
    val success: Boolean,
    @Expose
    @SerializedName("products")
    val products: List<Products>
)