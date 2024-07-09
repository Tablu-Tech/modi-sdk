package com.tablutech.modisdk.ocr

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import com.regula.documentreader.api.DocumentReader
import com.regula.documentreader.api.completions.IDocumentReaderCompletion
import com.regula.documentreader.api.config.ScannerConfig
import com.regula.documentreader.api.enums.DocReaderAction
import com.regula.documentreader.api.enums.Scenario
import com.regula.documentreader.api.enums.eGraphicFieldType
import com.tablutech.modisdk.R


object OCRReader {

    val documentData = mutableMapOf<String, String>()

    fun documentReaderFront(context: Context, onResult: (Bitmap) -> Unit) : MutableMap<String, String> {

        DocumentReader.Instance().functionality().edit().setShowSkipNextPageButton(true).apply()
        DocumentReader.Instance().customization().edit()
            .setMultipageButtonBackgroundColor("#ff0000").apply()
        val document = DocumentReader.Instance()

        document.checkDatabaseUpdate(context, "Full") {
            it?.let {
                Log.d("DatabaseUpdate", "Date: " + it.date)
            }
        }

        document.customization().edit().setStatus("Procurando pelo documento...").apply()
        document.functionality().edit().setShowSkipNextPageButton(true).apply()
        val scannerConfig = ScannerConfig.Builder(Scenario.SCENARIO_OCR).build()
        document.showScanner(
            context,
            scannerConfig,
            IDocumentReaderCompletion { action, results, error ->
                if (action == DocReaderAction.COMPLETE || action == DocReaderAction.TIMEOUT) {


                    documentData.clear()

                    for (fieldId in 1..683) {
                        val fieldValue = results?.getTextFieldValueByType(fieldId)
                        val fieldName = results?.getTextFieldByType(fieldId)
                        if (!fieldValue.isNullOrEmpty()) {
                            documentData[fieldName!!.getFieldName(context).toString()] = fieldValue
                        }
                    }

                    val allInfo =
                       documentData.entries.joinToString { "${it.key}: ${it.value}" }

                    documentData.entries.forEach { entry ->
                        Log.d("Resultado", "Field : ${entry.key.toUpperCase()} :,  ${entry.value}")
                    }
                    onResult(results!!.getGraphicFieldImageByType(eGraphicFieldType.GF_DOCUMENT_IMAGE)!!)
                }
            })

        return documentData;
    }


    fun documentReaderBack(context: Context, onResult: (Bitmap) -> Unit) : MutableMap<String, String> {

        val document = DocumentReader.Instance()

        document.checkDatabaseUpdate(context, "Full") {
            it?.let {
                Log.d("DatabaseUpdate", "Date: " + it.date)
            }
        }

        document.customization().edit().setStatus("Procurando pelo documento...").apply()
        document.functionality().edit().setShowSkipNextPageButton(true).apply()
        document.customization().edit()
            .setMultipageAnimationFrontImage(getDrawable(context, com.regula.documentreader.api.R.drawable.reg_id_back)).apply()
        val scannerConfig = ScannerConfig.Builder(Scenario.SCENARIO_FULL_PROCESS).build()
        document.showScanner(
            context,
            scannerConfig
        ) { action, results, error ->

            if (action == DocReaderAction.COMPLETE || action == DocReaderAction.TIMEOUT) {
                for (fieldId in 1..683) {
                    val fieldValue = results?.getTextFieldValueByType(fieldId)
                    val fieldName = results?.getTextFieldByType(fieldId)
                    if (!fieldValue.isNullOrEmpty()) {
                        documentData[fieldName!!.getFieldName(context).toString()] = fieldValue
                    }
                }
                val allInfo = documentData.entries.joinToString { "${it.key}: ${it.value}" }
                documentData.entries.forEach { entry ->
                    Log.d("Resultado", "Field : ${entry.key} :  ${entry.value}")
                }
                onResult(results!!.getGraphicFieldImageByType(eGraphicFieldType.GF_DOCUMENT_IMAGE)!!)
            }
        }
        return documentData;
    }
}