package com.tablutech.modisdk.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tablutech.modisdk.ui.screens.DocumentChoose
import com.tablutech.modisdk.ui.screens.EndPage
import com.tablutech.modisdk.ui.screens.OCR

import com.tablutech.modisdk.ui.screens.Onboarding
import com.tablutech.modisdk.ui.screens.ProcessingPage
import com.tablutech.modisdk.ui.screens.Screen

@Composable
fun OnboardingSDK(
//    loginViewModel: LoginViewModel,
//    sharedViewModel: SharedViewModel,
//    starScreen: String =  Screen.SplashScreen.route,
//    requestPermissions: () -> Unit,
) {

    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Screen.Onboarding.route) {
        composable(route = Screen.Onboarding.route) {
            Onboarding(navController = navController)
        }

        composable(route = Screen.ProcessingPage.route) {
            ProcessingPage(navController = navController)
        }

        composable(route = Screen.DocumentChoose.route) {
            DocumentChoose(navController = navController)
        }
        composable(route = Screen.OCR.route) {
            OCR(navController = navController)
        }

        composable(route = Screen.EndPage.route) {
            EndPage(navController = navController)
        }
    }
}