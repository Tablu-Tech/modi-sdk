package com.tablutech.modi_agentapp.data.repository

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.tablutech.modi_agentapp.data.remote.SubscriberApiService
import com.tablutech.modisdk.data.model.Subscritor

import com.tablutech.modisdk.utils.Constants
import com.tablutech.modisdk.utils.Constants.TAGMODI
import com.tablutech.modisdk.utils.Helpers.bitmapToTempFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.POST
import java.io.File
import java.util.concurrent.TimeUnit


class SubscriberRepositoryImpl() {


    suspend fun searchSubscriber(
        context: Context,
        selfie: Bitmap,
    ): Response<Subscritor> = withContext(Dispatchers.IO) {

        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()


        val apiUrl = "https://six.modi.tablu.tech/api/search_biometry_person"


        val requestBodyBuilder = MultipartBody.Builder().setType(MultipartBody.FORM)



        Log.d(TAGMODI, "MoDi searching for subscriber")


        val file1 = bitmapToTempFile(context, selfie)
        val requestBody1 = RequestBody.create("image/*".toMediaTypeOrNull(), file1)
        val part1 = MultipartBody.Part.createFormData("face_picture", "face_picture.jpg", requestBody1)
        requestBodyBuilder.addPart(part1)


        val requestBody: RequestBody = requestBodyBuilder.build()

        val request: Request = Request.Builder()
            .url(apiUrl)
            .addHeader("Authorization", "Bearer ${Constants.token}")
            .post(requestBody)
            .build()

        return@withContext try {
            val response = client.newCall(request).execute()
            response.use {
                try {
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        val subscriberResponse =
                            Gson().fromJson(responseBody, Subscritor::class.java)
                        Log.d(TAGMODI, "Response: $subscriberResponse")
                        Response.success(subscriberResponse)
                    } else {
                        Log.e("APIResponse", "Error response code: ${response.code}")
                        val errorResponse = response.body?.string()
                        Log.e("APIResponse", "Error response body: $errorResponse")
                        Response.error<Subscritor?>(
                            response.code,
                            ResponseBody.create(null, errorResponse.orEmpty())
                        )
                    }
                } catch (e: JsonSyntaxException) {
                    Log.e("APIResponse", "Error parsing JSON", e)
                    Response.error<Subscritor?>(500, ResponseBody.create(null, e.message.orEmpty()))
                } catch (e: Exception) {
                    Log.e("APIResponse", "Error making network request", e)
                    Response.error<Subscritor?>(500, ResponseBody.create(null, e.message.orEmpty()))
                }
            }
        } catch (e: Exception) {
            Log.e("APIResponse", "Error making network request", e)
            Response.error<Subscritor?>(500, ResponseBody.create(null, e.message.orEmpty()))
        }
    }

    fun prepareFilePart(partName: String, file: File): MultipartBody.Part {
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(partName, file.name, requestFile)
    }

    fun preparePart(data: String): RequestBody {
        return data.toRequestBody("text/plain".toMediaTypeOrNull())
    }


}