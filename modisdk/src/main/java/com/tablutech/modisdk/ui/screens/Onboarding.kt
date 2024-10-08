package com.tablutech.modisdk.ui.screens

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tablutech.modisdk.ui.components.BottomBar
import com.tablutech.modisdk.ui.components.InfoCards
import com.tablutech.modisdk.ui.components.TextHeaders
import com.tablutech.modisdk.ui.components.TopAppBarCustom
import com.tablutech.modisdk.*
import com.tablutech.modisdk.liveness.LivenessDetetion
import com.tablutech.modisdk.utils.Constants

@Preview
@Composable
fun Onboarding(navController: NavController? = null, onImageCaptured: (Bitmap) -> Unit = {}) {
    Scaffold(
        topBar = { TopAppBarCustom() },
        bottomBar = {
            BottomBar(navigationBack = {
                navController!!.popBackStack()
            }, navigation = {
                LivenessDetetion.passive(context = navController?.context!!) {
                    onImageCaptured(it)
                    Constants.faceBitmap = it
                    navController.navigate(Screen.ProcessingPage.route)
                }
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
                InfoCards(
                    painter = painterResource(id = R.drawable.provadevida),
                    textTitle = "Verso do documento",
                )
                Spacer(modifier = Modifier.height(32.dp))
                InfoCards(
                    painter = painterResource(id = R.drawable.frontbi),
                    textTitle = "Frente do documento",
                    textDescription = "Faça o scan do verso do seu documento."
                )

                Spacer(modifier = Modifier.height(32.dp))
                InfoCards(
                    painter = painterResource(id = R.drawable.frontbi),
                    textTitle = "Verso do documento",
                    textDescription = "Faça o scan do verso do seu documento."
                )
            }
        }
    }
}