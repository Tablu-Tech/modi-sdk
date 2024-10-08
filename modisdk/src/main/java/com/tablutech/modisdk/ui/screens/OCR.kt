package com.tablutech.modisdk.ui.screens

import android.graphics.Bitmap
import android.os.Handler
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tablutech.modisdk.R
import com.tablutech.modisdk.data.model.Subscritor
import com.tablutech.modisdk.facematch.FaceMacth.matchFaces
import com.tablutech.modisdk.ocr.OCRReader
import com.tablutech.modisdk.ocr.OCRReader.documentReaderBack
import com.tablutech.modisdk.ocr.OCRReader.documentReaderFront
import com.tablutech.modisdk.utils.Constants.documentTypeID
import com.tablutech.modisdk.ui.components.BottomBar
import com.tablutech.modisdk.ui.components.CardKYCComponents
import com.tablutech.modisdk.ui.components.ProcessingDialog
import com.tablutech.modisdk.ui.components.TopAppBarCustom
import com.tablutech.modisdk.ui.components.ValidationDialog
import com.tablutech.modisdk.utils.Constants
import com.tablutech.modisdk.utils.Constants.TAGMODI

@Preview
@Composable
fun OCR(
    navController: NavController? = null,
    onImageCaptured: (Bitmap) -> Unit = {}
) {

    val rememberState = rememberSaveable { mutableStateOf(false) }
    val verifiedDocumentFront = rememberSaveable { mutableStateOf(false) }
    val verifiedDocumentBack = rememberSaveable { mutableStateOf(false) }




    Scaffold(
        topBar = { TopAppBarCustom(label = "Captura de documentos") },
        bottomBar = {
            BottomBar(
                navigationBack = {
                    navController!!.popBackStack()
                },
                navigation = {
                    if ((verifiedDocumentFront.value && verifiedDocumentBack.value) || (verifiedDocumentFront.value && !documentTypeID.equals("Bilhete de identidade",true))) {
                        navController!!.navigate(Screen.ProcessingPageScreen.route)
                    } else {
                        Log.d(TAGMODI,
                            "Else: verifiedDocumentFront: ${verifiedDocumentFront.value} && verifiedDocumentBack :${verifiedDocumentBack.value}"
                        )
                    }
                }
            )
        }) { innerPadding ->



        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = com.tablutech.modisdk.R.drawable.bg),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(220.dp)
                    .graphicsLayer(alpha = 0.32f)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 56.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 32.dp, start = 0.dp)
                        .weight(1f)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            CardKYCComponents(
                                text = "Frente",
                                R.drawable.front_disable,
                                R.drawable.front_filled,
                                verifiedDocumentFront.value,
                                true
                            ) {

                                documentReaderFront(context = navController?.context!!) {
                                    if (it != null) verifiedDocumentFront.value =
                                        true else verifiedDocumentFront.value = false;
                                    Constants.documentProtaitBitmap = it
                                }

                            }

                            if (documentTypeID == "Bilhete de identidade")
                                CardKYCComponents(
                                    text = "Verso", R.drawable.back_disable,
                                    R.drawable.back_filled, verifiedDocumentBack.value, true
                                ) {
                                    documentReaderBack(context = navController?.context!!) {
                                        if (it != null) verifiedDocumentBack.value =
                                            true else verifiedDocumentBack.value = false;
                                    }
                                }
                        }
                    }
                }
            }
        }
    }
}