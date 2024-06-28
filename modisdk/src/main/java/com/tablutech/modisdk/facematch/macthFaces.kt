package com.tablutech.modisdk.facematch

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import com.regula.facesdk.FaceSDK
import com.regula.facesdk.detection.request.OutputImageCrop
import com.regula.facesdk.detection.request.OutputImageParams
import com.regula.facesdk.enums.ImageType
import com.regula.facesdk.enums.OutputImageCropAspectRatio
import com.regula.facesdk.model.MatchFacesImage
import com.regula.facesdk.model.results.matchfaces.MatchFacesResponse
import com.regula.facesdk.model.results.matchfaces.MatchFacesSimilarityThresholdSplit
import com.regula.facesdk.request.MatchFacesRequest

object FaceMacth {
    fun matchFaces(first: Bitmap, second: Bitmap, onResult: (Double) -> Unit) {
        val firstImage = MatchFacesImage(first, ImageType.PRINTED, true)
        val secondImage = MatchFacesImage(second, ImageType.PRINTED, true)
        val matchFacesRequest = MatchFacesRequest(arrayListOf(firstImage, secondImage))

        val crop = OutputImageCrop(
            OutputImageCropAspectRatio.OUTPUT_IMAGE_CROP_ASPECT_RATIO_3X4
        )
        val outputImageParams = OutputImageParams(crop, Color.WHITE)
        matchFacesRequest.outputImageParams = outputImageParams

        FaceSDK.Instance().matchFaces(matchFacesRequest) { matchFacesResponse: MatchFacesResponse ->
            val split = MatchFacesSimilarityThresholdSplit(matchFacesResponse.results, 0.75)
            val similarity = if (split.matchedFaces.size > 0) {
                split.matchedFaces[0].similarity
            } else if (split.unmatchedFaces.size > 0) {
                split.unmatchedFaces[0].similarity
            } else {
                null
            }

            Log.d("Similarity", "Similarity: $similarity")


            val formattedOutput = String.format("%.2f", similarity!! * 100)
            val percentageDouble = formattedOutput.toDouble()
            Log.d("Similarity", "Similarity: $percentageDouble %")

            onResult(percentageDouble)
        }
    }
}