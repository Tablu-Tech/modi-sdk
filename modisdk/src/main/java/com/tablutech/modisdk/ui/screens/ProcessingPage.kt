package com.tablutech.modisdk.ui.screens

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import com.tablutech.modi_agentapp.data.repository.SubscriberRepositoryImpl
import com.tablutech.modisdk.R
import com.tablutech.modisdk.data.model.Subscritor
import com.tablutech.modisdk.ocr.OCRReader
import com.tablutech.modisdk.ui.themes.GreenModi
import com.tablutech.modisdk.ui.themes.textInfoColor
import com.tablutech.modisdk.utils.Constants
import com.tablutech.modisdk.ui.components.BottomBarCancel
import com.tablutech.modisdk.ui.components.TopAppBarCustom

@Preview
@Composable
fun ProcessingPage (navController: NavController? = null, onImageCaptured: (Bitmap) -> Unit = {},
                    onOnboardingCompleted: (navController: NavController?, documentData: MutableMap<String, String>?, protaitBitmap: Bitmap?, faceBitmap: Bitmap?, documentFrontBitmap: Bitmap?, documentBackBitmap: Bitmap?, subscriber: Subscritor? ) -> Unit = {
                        navController, documentData, protaitBitmap, faceBitmap, documentFrontBitmap, documentBackBitmap, subscriber ->
                    } ) {

    var subscritor by remember { mutableStateOf<Subscritor?>(null) }

    Scaffold(
        topBar = { TopAppBarCustom() },
        bottomBar = {
            BottomBarCancel(navigationBack = {
                navController!!.popBackStack()
            }, navigation = {
            })
        },
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            Column (modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (0.30f * 400.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 34.dp, end = 34.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically)
                {
                    Image(painter = painterResource(id = R.drawable.checked), contentDescription = null, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Avaliando a prova de vida")
                }

                Spacer(modifier = Modifier.height(32.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 34.dp, end = 34.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically)
                {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), color = GreenModi)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Pesquisando trabalhador")
                }


                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 70.dp, start = 34.dp, end = 34.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically)
                {
                    CircularProgressIndicator(color = GreenModi)
                }


                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 70.dp, start = 34.dp, end = 34.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically)
                {
                    Text(text = "Esse processo pode demorar alguns\nminutos" , color = textInfoColor,  modifier = Modifier.graphicsLayer(alpha = 0.3f), textAlign = TextAlign.Center)
                }

                LaunchedEffect(Unit) {
                    subscritor =
                        SubscriberRepositoryImpl().searchSubscriber(navController!!.context, Constants.faceBitmap!!)
                            .body()

                    if(subscritor!=null){
                        Constants.subscritor = subscritor
                       // navController.navigate(Screen.DocumentChoose.route)
                        onOnboardingCompleted(navController, OCRReader.documentData, Constants.documentProtaitBitmap, Constants.faceBitmap, Constants.documentFrontBitmap, Constants.documentBackBitmap, subscritor)
                    }else{
                        navController.navigate(Screen.DocumentChoose.route)
                    }
                }
            }
        }
    }
}