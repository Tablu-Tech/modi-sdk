package com.tablutech.modisdk.ui.components

import android.graphics.Bitmap
import android.text.Layout
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.tablutech.modisdk.R
import com.tablutech.modisdk.ui.screens.Screen
import com.tablutech.modisdk.ui.themes.ButtonOutlineColor
import com.tablutech.modisdk.ui.themes.GreenModi
import com.tablutech.modisdk.ui.themes.textInfoColor

@Composable
fun ProcessingDialog(showDialog: Boolean = true) {
    if (showDialog) {
        // Define states for each processing step
        val step1Visible = remember { mutableStateOf(true) }
        val step2Visible = remember { mutableStateOf(false) }
        val step3Visible = remember { mutableStateOf(false) }

        // Define delays between steps
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(3000) // Delay for 2 seconds
            step1Visible.value = false
            step2Visible.value = true

            kotlinx.coroutines.delay(3000) // Delay for another 2 seconds
            step2Visible.value = false
            step3Visible.value = true
        }

        Dialog(onDismissRequest = { /* Do nothing or handle dismiss */ }) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(259.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    AnimatedVisibility(
                        visible = step1Visible.value,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start, modifier = Modifier
                            .padding(bottom = 16.dp, start = 40.dp)
                            .fillMaxWidth()) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.width(16.dp))
                            Text("Processando os documentos")
                        }
                    }
                    AnimatedVisibility(
                        visible = step2Visible.value,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start, modifier = Modifier
                            .padding(bottom = 16.dp, start = 40.dp)
                            .fillMaxWidth()) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.width(16.dp))
                            Text("Fazendo o OCR")
                        }
                    }
                    AnimatedVisibility(
                        visible = step3Visible.value,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start, modifier = Modifier
                            .padding(bottom = 16.dp, start = 40.dp)
                            .fillMaxWidth()) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.width(16.dp))
                            Text("Fazendo Face Match")
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun ValidationDialog(
    documentProtatitBitmap: Bitmap? = null,
    faceBitmap: Bitmap? =null,
    percentagem : String = "72",
    navController: NavController? = null
) {

    var showDialog by remember { mutableStateOf(true) }

    if (showDialog)
        Dialog(onDismissRequest = { showDialog = false}) {
            Surface(shape = MaterialTheme.shapes.medium, color = Color.White) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Image(
                        painter = painterResource(R.drawable.checked),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(text = buildAnnotatedString {
                        append("Confirmação de identidade com sucesso com ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,  color = if(percentagem.toDouble()>=60) GreenModi else Color.Red)) {
                            append(text = percentagem+"% ")
                        }
                        append("de nível de similaridade!")

                    }, textAlign = TextAlign.Center, color = textInfoColor)

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier =   Modifier.padding(start = 24.dp, end = 24.dp)) {
                        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
                            Text(text = "documento")
                            Image(
                                bitmap = (documentProtatitBitmap)!!.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier.size(120.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.End) {
                            Text(text = "foto da face ")
                            Image(
                                bitmap = (faceBitmap)!!.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier.size(120.dp)
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(20.dp))



                    Row(horizontalArrangement = Arrangement.Center, modifier =   Modifier.padding(start = 24.dp, end = 24.dp, bottom = 16.dp)) {
                        ButtonFilled(onClick = {
                            navController!!.navigate(Screen.EndPage.route)
                        }, text = "Feito", modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)

                            .border(
                                width = 1.dp,
                                color = GreenModi,
                                shape = RoundedCornerShape(8.dp)
                            )
                        )
                    }
                }
            }
        }
}

@Preview
@Composable
fun ProcessingDialogPreview() {
    ProcessingDialog(showDialog = true)
}
