package com.tablutech.modi_agentapp.domain.repository

import android.graphics.Bitmap
import com.tablutech.modi_agentapp.data.remote.ApiModel.response.OtpValidationResponse
import com.tablutech.modisdk.data.model.Subscritor
import retrofit2.Response
import java.util.concurrent.Flow.Subscriber

interface SubscriberRepository  {

    suspend fun saveSubscriber(
        subscriber: Subscritor,
        value: Bitmap?,
        value1: Bitmap?,
        value2: Bitmap?,
        token: String
    ): Response<Subscritor>

    suspend fun validateOtp(otp : String , number : String) : Response<OtpValidationResponse>
}