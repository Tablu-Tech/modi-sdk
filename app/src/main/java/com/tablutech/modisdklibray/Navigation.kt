package com.tablutech.modisdklibray

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tablutech.modisdklibray.screens.DocumentChoose
import com.tablutech.modisdklibray.screens.EndPage
import com.tablutech.modisdklibray.screens.OCR

import com.tablutech.modisdklibray.screens.Onboarding
import com.tablutech.modisdklibray.screens.ProcessingPage
import com.tablutech.modisdklibray.screens.Screen

@Composable
fun Navigation(
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