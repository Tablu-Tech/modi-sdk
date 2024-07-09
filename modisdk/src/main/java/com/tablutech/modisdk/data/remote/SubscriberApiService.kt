package com.tablutech.modi_agentapp.data.remote

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.navigation.NavController
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.tablutech.modisdk.data.model.Subscritor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.util.concurrent.TimeUnit

interface SubscriberApiService{

    @Multipart
    @POST("add_subscriber")
    suspend fun addSubscriber(
        @Part("name") name: RequestBody,
        @Part("first_name") first_name: RequestBody,
        @Part("middle_name") middle_name: RequestBody,
        @Part("last_name") last_name: RequestBody,
        @Part("phone_number") phone_number: RequestBody,
        @Part("document_number") document_number: RequestBody,
        @Part("email") email: RequestBody,
        @Part("user_id") user_id: RequestBody,
        @Part("nutel") nutel: RequestBody,
        @Part("nuit") nuit: RequestBody,
        @Part("nuib") nuib: RequestBody,
        @Part("serial_number") serial_number: RequestBody,
        @Part("height") height: RequestBody,
        @Part("birth_date") birth_date: RequestBody,
        @Part("father_name") father_name: RequestBody,
        @Part("mother_name") mother_name: RequestBody,
        @Part("has_selfie") has_selfie: RequestBody,
        @Part("marital_status") marital_status: RequestBody,
        @Part("country") country: RequestBody,
        @Part("city") city: RequestBody,
        @Part("district") district: RequestBody,
        @Part("house_number") house_number: RequestBody,
        @Part("physical_address") physical_address: RequestBody,
        @Part("issuance_date") issuance_date: RequestBody,
        @Part("expire_date") expire_date: RequestBody,
        @Part("nationality") nationality: RequestBody,
        @Part front_side: MultipartBody.Part,
        @Part back_side: MultipartBody.Part,
        @Part selfie: MultipartBody.Part
    ): Response<Subscritor>
}
