package com.tablutech.modisdk

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.regula.documentreader.api.DocumentReader
import com.regula.documentreader.api.completions.IDocumentReaderPrepareCompletion
import com.regula.documentreader.api.errors.DocumentReaderException
import com.regula.documentreader.api.params.DocReaderConfig
import com.regula.facesdk.FaceSDK
import com.regula.facesdk.configuration.InitializationConfiguration
import com.regula.facesdk.exception.InitException

object ModiSDK {

    fun Initialize(context: Context){

        val licInput = context.resources.openRawResource(com.tablutech.modisdk.R.raw.regula)
        val available = licInput.available()
        val license = ByteArray(available)

        licInput.read(license)

        DocumentReader.Instance()
            .prepareDatabase(context, "Full", object : IDocumentReaderPrepareCompletion {
                override fun onPrepareCompleted(p0: Boolean, p1: DocumentReaderException?) {
                    Log.d("REGULA", "Database prepared : $p0")
                }

                override fun onPrepareProgressChanged(progress: Int) {
                    Log.d("REGULA", "Database preparing: $progress")
                }
            })

        val configuration =
            InitializationConfiguration.Builder(license).setLicenseUpdate(false).build()


        FaceSDK.Instance().initialize(context, configuration) { status: Boolean, e: InitException? ->
            if (!status) {
                Toast.makeText(
                    context,
                    "Init finished with error: " + if (e != null) e.message else "",
                    Toast.LENGTH_LONG
                ).show()
                return@initialize
            }
            Log.d("MainActivity", "FaceSDK init completed successfully")
        }

        DocumentReader.Instance().initializeReader(
            context, DocReaderConfig(license)
        ) { success, error_initializeReader ->
            if (success) {
                Log.d("REGULA", "Database initialization was successful")
            } else {
                Log.d("REGULAREGULA", "initialization was ${error_initializeReader!!.message}")
            }
        }
    }
}