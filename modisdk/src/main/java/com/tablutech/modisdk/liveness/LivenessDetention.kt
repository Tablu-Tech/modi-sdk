package com.tablutech.modisdk.liveness

import android.content.Context
import android.graphics.Bitmap
import com.regula.facesdk.FaceSDK
import com.regula.facesdk.configuration.LivenessConfiguration
import com.regula.facesdk.configuration.UiConfiguration
import com.regula.facesdk.enums.CustomizationColor
import com.regula.facesdk.enums.CustomizationFont
import com.regula.facesdk.enums.LivenessType
import com.regula.facesdk.model.results.LivenessResponse
import com.tablutech.modisdk.R
import com.tablutech.modisdk.utils.LivenessResponseUtil
import org.json.JSONObject

object LivenessDetetion {

    fun passive(context: Context, onImageCaptured: (Bitmap) -> Unit) {

        var imageBitmap: Bitmap? = null
        val uiConfiguration = UiConfiguration.Builder()


            .setFont(CustomizationFont.ONBOARDING_SCREEN_TITLE_LABEL, R.font.montserrat_medium)
            .setColor(CustomizationColor.ONBOARDING_SCREEN_TITLE_LABEL_TEXT, R.color.green)
            .setColor(CustomizationColor.ONBOARDING_SCREEN_START_BUTTON_BACKGROUND, R.color.green)

            .setColor(CustomizationColor.CAMERA_SCREEN_FRONT_HINT_LABEL_TEXT, R.color.black)
            .setFont(CustomizationFont.CAMERA_SCREEN_HINT_LABEL, R.font.montserrat_light)


            .setColor(CustomizationColor.CAMERA_SCREEN_STROKE_ACTIVE, R.color.greendark)
            .setColor(CustomizationColor.CAMERA_SCREEN_STROKE_NORMAL, R.color.green)
            .setColor(CustomizationColor.CAMERA_SCREEN_SECTOR_ACTIVE, R.color.greendark)
            .setColor(CustomizationColor.CAMERA_SCREEN_SECTOR_TARGET, R.color.green)

            .setColor(CustomizationColor.PROCESSING_SCREEN_BACKGROUND, R.color.green)
            .setColor(CustomizationColor.PROCESSING_SCREEN_PROGRESS, R.color.green)
            .setColor(CustomizationColor.PROCESSING_SCREEN_TITLE, R.color.black)
            .setFont(CustomizationFont.PROCESSING_SCREEN, R.font.montserrat_medium)

            .build()

        val jsonString =
            "{ \"objects\": [ { \"label\": { \"text\": \"Place the document\\ninside the camera frame\", \"position\": { \"h\": 1.0, \"v\": 1.9 }, \"background\": \"#00FFFFFF\", \"fontColor\": \"#FFEB6517\", \"fontSize\": 18, \"alignment\": \"bottom\" } } ] }"
        val jsonObject = JSONObject(jsonString)

        val configuration: LivenessConfiguration = LivenessConfiguration.Builder()
            .setType(LivenessType.PASSIVE)
            .setCameraSwitchEnabled(true)
            .build()

        var faceSDK = FaceSDK.Instance()
        faceSDK.customization.setUiConfiguration(uiConfiguration)
        //   faceSDK.customization.setUiCustomizationLayer(jsonObject)
        faceSDK.startLiveness(context, configuration, { livenessResponse: LivenessResponse ->
            LivenessResponseUtil.response(context, livenessResponse)
            if (livenessResponse.bitmap != null)
                onImageCaptured(livenessResponse.bitmap!!)
        })
    }


    fun active(context: Context, onImageCaptured: (Bitmap) -> Unit) {

        var imageBitmap: Bitmap? = null
        val uiConfiguration = UiConfiguration.Builder()


            .setFont(CustomizationFont.ONBOARDING_SCREEN_TITLE_LABEL, R.font.montserrat_medium)
            .setColor(CustomizationColor.ONBOARDING_SCREEN_TITLE_LABEL_TEXT, R.color.green)
            .setColor(CustomizationColor.ONBOARDING_SCREEN_START_BUTTON_BACKGROUND, R.color.green)

            .setColor(CustomizationColor.CAMERA_SCREEN_FRONT_HINT_LABEL_TEXT, R.color.black)
            .setFont(CustomizationFont.CAMERA_SCREEN_HINT_LABEL, R.font.montserrat_light)

            .setColor(CustomizationColor.CAMERA_SCREEN_STROKE_ACTIVE, R.color.greendark)
            .setColor(CustomizationColor.CAMERA_SCREEN_STROKE_NORMAL, R.color.green)
            .setColor(CustomizationColor.CAMERA_SCREEN_SECTOR_ACTIVE, R.color.greendark)
            .setColor(CustomizationColor.CAMERA_SCREEN_SECTOR_TARGET, R.color.green)

            .setColor(CustomizationColor.PROCESSING_SCREEN_BACKGROUND, R.color.green)
            .setColor(CustomizationColor.PROCESSING_SCREEN_PROGRESS, R.color.green)
            .setColor(CustomizationColor.PROCESSING_SCREEN_TITLE, R.color.black)
            .setFont(CustomizationFont.PROCESSING_SCREEN, R.font.montserrat_medium)

            .build()

        val jsonString =
            "{ \"objects\": [ { \"label\": { \"text\": \"Place the document\\ninside the camera frame\", \"position\": { \"h\": 1.0, \"v\": 1.9 }, \"background\": \"#00FFFFFF\", \"fontColor\": \"#FFEB6517\", \"fontSize\": 18, \"alignment\": \"bottom\" } } ] }"
        val jsonObject = JSONObject(jsonString)

        val configuration: LivenessConfiguration = LivenessConfiguration.Builder()
            .setType(LivenessType.ACTIVE)
            .setCameraSwitchEnabled(true)
            .build()

        var faceSDK = FaceSDK.Instance()
        faceSDK.customization.setUiConfiguration(uiConfiguration)
        //   faceSDK.customization.setUiCustomizationLayer(jsonObject)
        faceSDK.startLiveness(context, configuration, { livenessResponse: LivenessResponse ->
            LivenessResponseUtil.response(context, livenessResponse)
            if (livenessResponse.bitmap != null)
                onImageCaptured(livenessResponse.bitmap!!)
        })
    }
}
