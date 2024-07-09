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



@Preview
@Composable
fun DocumentChoose (navController: NavController? = null, onImageCaptured: (Bitmap) -> Unit = {} ) {

    var selectedOption = rememberSaveable { mutableStateOf("Bilhete de identidade") }
    Scaffold(
        topBar = { TopAppBarCustom() },
        bottomBar = {
            BottomBar(navigationBack = {}, navigation = {
               navController!!.navigate(Screen.OCR.route)
            })
        },
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            Column (Modifier.padding(start = 24.dp, end = 24.dp), horizontalAlignment = Alignment.Start){
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
                            selected = selectedOption.value == "Bilhete de identidade",
                            onClick = { selectedOption.value = "Bilhete de identidade" })

                        Text(text = "Bilhete de identidade")
                    };

                    Row(
                        modifier = Modifier
                            .height(44.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        RadioButton(
                            selected = selectedOption.value == "Passaporte",
                            onClick = { selectedOption.value = "Passaporte" }, enabled = false
                        )

                        Text(text = "Passaporte")
                    };

                    Row(
                        modifier = Modifier
                            .height(44.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        RadioButton(
                            selected = selectedOption.value == "Carta de condução",
                            onClick = { selectedOption.value = "Carta de condução" }, enabled = false
                        )

                        Text(text = "Carta de condução")
                    };
                }
            }
        }
    }
}