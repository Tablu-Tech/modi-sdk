package com.tablutech.modisdklibray.screens

sealed class Screen (val route : String){
    object Onboarding : Screen(route = "onboard_ding")

}