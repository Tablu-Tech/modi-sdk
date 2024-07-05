package com.tablutech.modisdklibray.screens

import android.graphics.Bitmap
import android.os.Handler
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.tablutech.modisdk.facematch.FaceMacth.matchFaces
import com.tablutech.modisdk.ocr.OCRReader.documentReaderBack
import com.tablutech.modisdk.ocr.OCRReader.documentReaderFront
import com.tablutech.modisdk.utils.Constants.documentTypeID
import com.tablutech.modisdklibray.R
import com.tablutech.modisdklibray.components.BottomBar
import com.tablutech.modisdklibray.components.CardKYCComponents
import com.tablutech.modisdklibray.components.ProcessingDialog
import com.tablutech.modisdklibray.components.TopAppBarCustom

@Preview
@Composable
fun OCR (navController: NavController? = null, onImageCaptured: (Bitmap) -> Unit = {} ) {

    val rememberState = rememberSaveable { mutableStateOf(false) }
    val verifiedDocumentFront = rememberSaveable { mutableStateOf(false) }
    val verifiedDocumentBack = rememberSaveable { mutableStateOf(false) }
    val verifiedFace = rememberSaveable { mutableStateOf(false) }
    val imageCaptured = rememberSaveable {  mutableStateOf<Bitmap?>(null)  }
    var handler = Handler()
    var match = rememberSaveable {mutableStateOf(0.0)}
    var dialogState = rememberSaveable {mutableStateOf(false)}
    val isProcessing  = false


    Scaffold(
        topBar = { TopAppBarCustom( label = "Captura de documentos") },
        bottomBar = { BottomBar(
            navigationBack = {},
            navigation = {

                navController!!.navigate(Screen.EndPage.route)

//                if(verifiedDocumentFront.value && verifiedDocumentBack.value && verifiedFace.value){
//                    sharedViewModel.setProcessing(true)
//
//                    if (sharedViewModel.frontBitmap.value!! !=null && sharedViewModel.selfieBitmap.value!! != null)
//                        matchFaces(sharedViewModel.frontBitmap.value!!, sharedViewModel.selfieBitmap.value!!) {
//                            match.value = it
//                            if (it >= Constants.similarity) {
//                                sharedViewModel.updateSimilarity(similarity = it)
//                                sharedViewModel.setProcessing(false)
//                                navController!!.navigate(Screen.Step3firstOption.route)
//                            } else {
//
//                            }
//                        } else {
//                    }
//                }
            }
        )}) { innerPadding ->

//        if(match.value<=75.0 && match.value>0.000000000000000000000000000  ){
//            ValidationErrorDialog(errorMessage = "Nivel de similiarida muito baixa") {
//                dialogState.value=false
//            }
//        }

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
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            CardKYCComponents(text = "Frente", com.tablutech.modisdk.R.drawable.front_disable,  com.tablutech.modisdk.R.drawable.front_filled, verifiedDocumentFront.value, true){

                                documentReaderFront(context = navController?.context!!){
                                    if(it!=null) verifiedDocumentFront.value = true else verifiedDocumentFront.value = false;
                                }

                            }

                            if(documentTypeID == "Bilhete de identidade")
                                CardKYCComponents(text = "Verso", com.tablutech.modisdk.R.drawable.back_disable,com.tablutech.modisdk.R.drawable.back_filled, verifiedDocumentBack.value, true){
                                    documentReaderBack(context = navController?.context!!){
                                        if(it!=null) verifiedDocumentBack.value = true else verifiedDocumentBack.value = false;
                                    }
                                }
                        }



                        if (imageCaptured.value!=null){
                            Log.d("IMAGEG", "Step2: ${imageCaptured.value}")
                        }
                    }
                }
            }
        }
        ProcessingDialog(showDialog = isProcessing)
    }
}