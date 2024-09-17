package com.tablutech.modisdk.ui.screens

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tablutech.modisdk.ui.components.BottomBar
import com.tablutech.modisdk.ui.components.TextHeaders
import com.tablutech.modisdk.ui.components.TopAppBarCustom
import com.tablutech.modisdk.utils.Constants


@Preview
@Composable
fun DocumentChoose(navController: NavController? = null, onImageCaptured: (Bitmap) -> Unit = {}) {

    var selectedDocumentOption = rememberSaveable { mutableStateOf("Bilhete de identidade") }
    Scaffold(
        topBar = { TopAppBarCustom() },
        bottomBar = {
            BottomBar(navigationBack = {
                navController!!.popBackStack()
            }, navigation = {
                navController!!.navigate(Screen.OCR.route)
            })
        },
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                Modifier.padding(start = 24.dp, end = 24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                TextHeaders()
                Spacer(modifier = Modifier.height(32.dp))

                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier
                            .height(44.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(text = "Tipo de documento")
                    };



                    Row(
                        modifier = Modifier
                            .height(44.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        RadioButton(
                            selected = selectedDocumentOption.value == "Bilhete de identidade",
                            onClick = {
                                selectedDocumentOption.value = "Bilhete de identidade"
                                Constants.documentTypeID = "Bilhete de identidade"
                            }, enabled = true)

                        Text(text = "Bilhete de identidade")
                    };

//                    Row(
//                        modifier = Modifier
//                            .height(44.dp)
//                            .fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Start
//                    ) {
//                        RadioButton(
//                            selected = selectedDocumentOption.value == "Carta de condução",
//                            onClick = {
//                                selectedDocumentOption.value = "Carta de condução"
//                                Constants.documentTypeID = "Carta de condução"
//                            },
//                        )
//
//                        Text(text = "Carta de condução")
//                    };

                    Row(
                        modifier = Modifier
                            .height(44.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        RadioButton(
                            selected = selectedDocumentOption.value == "Cartão de eleitor",
                            onClick = {
                                selectedDocumentOption.value = "Cartão de eleitor"
                                Constants.documentTypeID = "Cartão de eleitor"
                            },
                        )

                        Text(text = "Cartão de eleitor")
                    };

//                    Row(
//                        modifier = Modifier
//                            .height(44.dp)
//                            .fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Start
//                    ) {
//
//                        RadioButton(
//                            selected = selectedDocumentOption.value == "Passaporte",
//                            onClick = {
//                                selectedDocumentOption.value = "Passaporte"
//                                Constants.documentTypeID = "Passaporte"
//                            },
//                        )
//
//                        Text(text = "Passaporte")
//                    };
                }
            }
        }
    }
}