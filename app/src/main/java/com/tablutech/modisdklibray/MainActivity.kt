package com.tablutech.modisdklibray

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.regula.documentreader.api.DocumentReader
import com.regula.documentreader.api.completions.IDocumentReaderPrepareCompletion
import com.regula.documentreader.api.errors.DocumentReaderException
import com.regula.documentreader.api.params.DocReaderConfig
import com.regula.facesdk.FaceSDK
import com.regula.facesdk.configuration.InitializationConfiguration
import com.regula.facesdk.configuration.LivenessConfiguration
import com.regula.facesdk.exception.InitException
import com.tablutech.modisdklibray.ui.theme.ModiSdkLibrayTheme
import java.io.File

class MainActivity : ComponentActivity() {

    private val requestCameraPermission =

        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
            }else{

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val licInput = resources.openRawResource(R.raw.regula)
        val available = licInput.available()
        val license = ByteArray(available)

        licInput.read(license)

        DocumentReader.Instance()
            .runAutoUpdate(this@MainActivity, "Full", object : IDocumentReaderPrepareCompletion {
                override fun onPrepareCompleted(p0: Boolean, p1: DocumentReaderException?) {
                    Log.d("REGULA", "Database prepared : $p0")
                }

                override fun onPrepareProgressChanged(progress: Int) {
                    Log.d("REGULA", "Database preparing: $progress")
                }
            })

        val configuration =
            InitializationConfiguration.Builder(license).setLicenseUpdate(false).build()


        FaceSDK.Instance().initialize(this, configuration) { status: Boolean, e: InitException? ->
            if (!status) {
                Toast.makeText(
                    this@MainActivity,
                    "Init finished with error: " + if (e != null) e.message else "",
                    Toast.LENGTH_LONG
                ).show()
                return@initialize
            }
            Log.d("MainActivity", "FaceSDK init completed successfully")
        }

        DocumentReader.Instance().initializeReader(
            this, DocReaderConfig(license)
        ) { success, error_initializeReader ->
            if (success) {
                Log.d("REGULA", "initialization was successful")
            } else {
                Log.d("REGULAREGULA", "initialization was ${error_initializeReader!!.message}")
            }
        }



        setContent {
            ModiSdkLibrayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    private fun requestPermissions() {
        requestCameraPermission.launch(Manifest.permission.CAMERA)
    }


    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this)
            .setTitle("Permission Denied")
            .setMessage("Camera permission is required for this feature. Please enable it in the app settings.")
            .setPositiveButton("Settings") { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply { data = Uri.fromParts("package", packageName, null) }
                startActivity(intent)
            }
            .setCancelable(false)
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        FaceSDK.Instance().deinitialize()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ModiSdkLibrayTheme {
        Greeting("Android")
    }
}