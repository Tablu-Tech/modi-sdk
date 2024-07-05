package com.tablutech.modisdk.utils

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Helpers {
    fun bitmapToTempFile(context: Context, bitmap: Bitmap): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile = File.createTempFile(
            imageFileName,
            ".jpg",
            storageDir
        )

        try {
            val fos = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, fos)
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return imageFile
    }
}