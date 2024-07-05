package com.tablutech.modisdklibray.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

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
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(bottom = 16.dp,  start = 40.dp).fillMaxWidth()) {
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
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(bottom = 16.dp,  start = 40.dp).fillMaxWidth()) {
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
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(bottom = 16.dp, start = 40.dp).fillMaxWidth()) {
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
fun ProcessingDialogPreview() {
    ProcessingDialog(showDialog = true)
}
