package com.bagicode.foodmarketkotlin.model.response.home

import android.os.Parcelable
import com.bagicode.foodmarketkotlin.model.response.login.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Products(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("image")
    val image : String,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("slug")
    val slug: String,
    @Expose
    @SerializedName("category_id")
    val category_id: Int,
    @Expose
    @SerializedName("content")
    val content: String,
    @Expose
    @SerializedName("weight")
    val weight: Int,
    @Expose
    @SerializedName("price")
    val price: Int,
    @Expose
    @SerializedName("discount")
    val discount: Int,
) : Parcelable