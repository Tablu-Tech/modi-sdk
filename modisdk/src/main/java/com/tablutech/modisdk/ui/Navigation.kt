package com.tablutech.modisdk.ui

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tablutech.modisdk.data.model.Subscritor
import com.tablutech.modisdk.ui.screens.DocumentChoose
import com.tablutech.modisdk.ui.screens.EndPage
import com.tablutech.modisdk.ui.screens.OCR

import com.tablutech.modisdk.ui.screens.Onboarding
import com.tablutech.modisdk.ui.screens.ProcessingPage
import com.tablutech.modisdk.ui.screens.ProcessingPageScreen
import com.tablutech.modisdk.ui.screens.Screen

@Composable
fun OnboardingSDK(
    onOnboardingCompleted: (navController: NavController? ,documentData: MutableMap<String, String>?, protaitBitmap: Bitmap?, faceBitmap: Bitmap?, documentFrontBitmap: Bitmap?, documentBackBitmap: Bitmap?, subscriber : Subscritor?) -> Unit = {navController,  documentData, protaitBitmap, faceBitmap, documentFrontBitmap, documentBackBitmap, subscriber -> }
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Onboarding.route) {
        composable(route = Screen.Onboarding.route) {
            Onboarding(navController = navController)
        }

        composable(route = Screen.ProcessingPage.route) {
            ProcessingPage(navController = navController,  onOnboardingCompleted = onOnboardingCompleted)
        }

        composable(route = Screen.ProcessingPageScreen.route) {
            ProcessingPageScreen(navController = navController, onOnboardingCompleted = onOnboardingCompleted)
        }

        composable(route = Screen.DocumentChoose.route) {
            DocumentChoose(navController = navController)
        }
        composable(route = Screen.OCR.route) {
            OCR(navController = navController, )
        }

        composable(route = Screen.EndPage.route) {
            EndPage(navController = navController)
        }
    }
}