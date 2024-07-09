package com.tablutech.modisdk.ui.screens

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tablutech.modisdk.liveness.LivenessDetetion
import com.tablutech.modisdk.utils.Constants
import com.tablutech.modisdk.ui.components.BottomBar

@Preview
@Composable
fun EndPage(navController: NavController? = null, onImageCaptured: (Bitmap) -> Unit = {}) {

    Scaffold(
        topBar = { },
        bottomBar = {
            BottomBar(navigationBack = {}, navigation = {
                LivenessDetetion.passive(context = navController?.context!!) {
                    onImageCaptured(it)
                    navController.navigate(Screen.DocumentChoose.route)
                }
            })
        },
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding), contentAlignment = Alignment.Center
        ) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Continua....", fontSize = 30.sp)

                Image(bitmap =  Constants.faceBitmap!!.asImageBitmap(), contentDescription = null , modifier = Modifier.size(300.dp))
                Row {
                    Text(text = "first name : ", fontSize = 12.sp)
                    Text(text = Constants.subscritor?.personalData?.first_name+ "", fontSize = 14.sp)
                }

                Row {
                    Text(text = "last name : ", fontSize = 12.sp)
                    Text(text = Constants.subscritor?.personalData?.last_name + " ", fontSize = 14.sp)
                }

                Row {
                    Text(text = "Nutel : ", fontSize = 12.sp)
                    Text(text = Constants.subscritor?.personalData?.nutel_person+"", fontSize = 14.sp)
                }

                Row {
                    Text(text = "birth day : ", fontSize = 12.sp)
                    Text(text = Constants.subscritor?.personalData?.birth_date+"", fontSize = 14.sp)
                }
            }
        }
    }
}

