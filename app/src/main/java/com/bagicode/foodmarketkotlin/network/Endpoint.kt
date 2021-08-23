package com.bagicode.foodmarketkotlin.network

import com.bagicode.foodmarketkotlin.model.response.ProfileResponse
import com.bagicode.foodmarketkotlin.model.response.Wrapper
import com.bagicode.foodmarketkotlin.model.response.checkout.CheckoutResponse
import com.bagicode.foodmarketkotlin.model.response.home.HomeResponse
import com.bagicode.foodmarketkotlin.model.response.home.ProductsResponse
import com.bagicode.foodmarketkotlin.model.response.login.LoginResponse
import com.bagicode.foodmarketkotlin.model.response.transaction.TransactionResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import java.util.*
import kotlin.collections.ArrayList

interface Endpoint {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("email") email: String,
              @Field("password") password: String): Observable<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    fun register(@Field("name") name: String,
              @Field("email") email: String,
              @Field("password") password: String,
              @Field("password_confirmation") password_confirmation: String): Observable<LoginResponse>

    @Multipart
    @POST("user/photo")
    fun registerPhoto(@Part profileImage: MultipartBody.Part): Observable<Wrapper<Any>>

    @GET("user")
    fun profile(): Observable<Wrapper<ProfileResponse>>

    @GET("food")
    fun home(): Observable<Wrapper<HomeResponse>>

    @GET("products")
    fun products(): Observable<ProductsResponse>

    // transaction,
    // Progress = on delivery
    // Past order = delivered
    @GET("transaction")
    fun transaction(): Observable<Wrapper<TransactionResponse>>

    // transaction update,
    @FormUrlEncoded
    @POST("transaction/{id}")
    fun transactionUpdate(@Path(value = "id") userId:String,
                          @Field("status") status: String): Observable<Wrapper<Any>>

    // checkout
    @FormUrlEncoded
    @POST("checkout")
    fun checkout(@Field("food_id") food_id: String,
                 @Field("user_id") user_id: String,
                 @Field("quantity") quantity: String,
                 @Field("total") total: String,
                 @Field("status") status: String): Observable<Wrapper<CheckoutResponse>>

}