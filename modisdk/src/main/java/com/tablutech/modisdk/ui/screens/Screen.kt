package com.tablutech.modisdk.ui.screens

sealed class Screen (val route : String){
    object Onboarding : Screen(route = "onboard_ding")
    object DocumentChoose : Screen(route = "document_choose")
    object OCR : Screen(route = "ocr")
    object ProcessingPage : Screen(route = "processing_page")
    object ProcessingPageScreen : Screen(route = "processing_page_screen")
    object EndPage : Screen(route = "end_page")
}