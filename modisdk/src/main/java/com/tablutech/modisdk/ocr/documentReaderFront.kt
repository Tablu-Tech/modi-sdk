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
import com.tablutech.modisdk.data.model.fieldsToRemove
import com.tablutech.modisdk.data.model.formatBornDate
import com.tablutech.modisdk.data.model.joinPersonalNames
import com.tablutech.modisdk.data.model.translateKey
import com.tablutech.modisdk.utils.Constants
import com.tablutech.modisdk.utils.Constants.TAGMODI


object OCRReader {

    val documentData = mutableMapOf<String, String>()

    fun documentReaderFront(context: Context, onResult: (Bitmap) -> Unit) : MutableMap<String, String> {

        DocumentReader.Instance().functionality().edit().setShowSkipNextPageButton(true).apply()
        DocumentReader.Instance().customization().edit()
            .setMultipageButtonBackgroundColor("#ff0000").apply()
        val document = DocumentReader.Instance()

        document.checkDatabaseUpdate(context, "Full") {
            it?.let {
                Log.d(TAGMODI, "Checking MoDi DatabaseUpdate: " + it.date)
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

                            var field =  fieldName!!.getFieldName(context).toString()

                            if (field !in fieldsToRemove)
                            documentData[translateKey(field)] = fieldValue
                        }
                    }

                    documentData["Nome"] = joinPersonalNames(documentData["Nome"], documentData["Sobrenome"])
                    documentData["Data de validade"] = formatBornDate(documentData["Data de validade"])
                    documentData["Data de emissão"] = formatBornDate(documentData["Data de emissão"])
                    documentData["Data de nascimento"] = formatBornDate(documentData["Data de nascimento"])
                    Log.d("Novo field", joinPersonalNames(documentData["Nome"], documentData["Sobrenome"]))
                    documentData.remove("Sobrenome")
                    documentData.remove("Sobrenome e nomes")

                    val allInfo =
                       documentData.entries.joinToString { "${translateKey(it.key)}: ${it.value}" }

                    documentData.entries.forEach { entry ->
                        Log.d("FIELDS", "Field : ${entry.key.toUpperCase()} :,  ${entry.value}")
                    }
                    Constants.documentFrontBitmap = results!!.getGraphicFieldImageByType(eGraphicFieldType.GF_DOCUMENT_IMAGE)
                    onResult(results!!.getGraphicFieldImageByType(eGraphicFieldType.GF_PORTRAIT)!!)
                }
            })

        return documentData;
    }


    fun documentReaderBack(context: Context, onResult: (Bitmap) -> Unit) : MutableMap<String, String> {
        val document = DocumentReader.Instance()
        document.checkDatabaseUpdate(context, "Full") {
            it?.let {
                Log.d(TAGMODI, "Checking MoDi DatabaseUpdate: " + it.date)
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
                        var field =  fieldName!!.getFieldName(context).toString()

                        if (field !in fieldsToRemove)
                        documentData[translateKey(field)] = fieldValue
                    }
                }

                documentData["Nome"] = joinPersonalNames(documentData["Nome"], documentData["Sobrenome"])
                Log.d("Novo field", joinPersonalNames(documentData["Nome"], documentData["Sobrenome"]))
                documentData["Data de validade"] = formatBornDate(documentData["Data de validade"])
                documentData["Data de emissão"] = formatBornDate(documentData["Data de emissão"])
                documentData["Data de nascimento"] = formatBornDate(documentData["Data de nascimento"])
                documentData.remove("Sobrenome")
                documentData.remove("Sobrenome e nomes")

                val allInfo = documentData.entries.joinToString { "${translateKey( it.key)}: ${it.value}" }
                documentData.entries.forEach { entry ->
                    Log.d("FIELDS", "Field : ${entry.key} :  ${entry.value}")
                }
                Constants.documentBackBitmap = results!!.getGraphicFieldImageByType(eGraphicFieldType.GF_DOCUMENT_IMAGE)
                onResult(results.getGraphicFieldImageByType(eGraphicFieldType.GF_DOCUMENT_IMAGE)!!)
            }
        }
        return documentData;
    }
}