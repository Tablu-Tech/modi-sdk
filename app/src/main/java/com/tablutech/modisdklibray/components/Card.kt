package com.tablutech.modisdklibray.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tablutech.modisdk.ui.themes.BlueDark
import com.tablutech.modisdk.ui.themes.ButtonOutlineColor
import com.tablutech.modisdklibray.R
import com.tablutech.modisdk.*


//@Composable
//@Preview
//fun CardOptionPhone(enable : Boolean = false){
//    Box(
//        modifier = Modifier
//            .height(150.dp)
//            .width(160.dp)
//            .border(
//                width = 1.dp,
//                color = if (enable) BlueDark else ButtonOutlineColor,
//                shape = RoundedCornerShape(5.dp)
//            ),
//        contentAlignment = Alignment.Center
//    ) {
//        Column (
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ){
//
//
//          //  Image(painter = painterResource(id = R.drawable.phone), contentDescription = null)
//            Text(text = "Telefone")
//        }
//    }
//}

//@Composable
//@Preview
//fun CardOptionTV(enable : Boolean = false){
//    Box(
//        modifier = Modifier
//            .height(150.dp)
//            .width(160.dp)
//            .border(
//                width = 1.dp,
//                color = if (enable) BlueDark else ButtonOutlineColor,
//                shape = RoundedCornerShape(5.dp)
//            ),
//        contentAlignment = Alignment.Center
//    ) {
//        Column (
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ){
//
//
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//
//                Image(painter = painterResource(id =  com.tablutech.modisdk.R.drawable.tv), contentDescription = null);
//                Spacer(modifier = Modifier.width(16.dp))
//                Image(painter = painterResource(id = R.drawable.phone), contentDescription = null)
//
//            }
//
//            Text(text = "TV e WEB")
//        }
//    }
//}


@Composable
fun CardKYCComponents(text : String, drawableBeforeVerified : Int , drawableAfterVerified : Int, isVerified : Boolean, required : Boolean = false, onClick:  () -> Unit){
    Box(
        modifier = Modifier
            .height(150.dp)
            .width(160.dp)
            .border(
                width = 1.dp,
                color = if (isVerified) BlueDark else ButtonOutlineColor,
                shape = RoundedCornerShape(5.dp)
            ).clickable {
                onClick()
            },


        ) {
        Column (
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp, top = 16.dp),
                horizontalArrangement = Arrangement.End

            ) {
                Image(
                    painter = painterResource( id = if(!isVerified)  com.tablutech.modisdk.R.drawable.info else com.tablutech.modisdk.R.drawable.checked),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            }

            Image(painter = painterResource(id = if (isVerified) drawableAfterVerified else drawableBeforeVerified), contentDescription = null, modifier = Modifier.size(height = 57.dp, width = 42.75.dp))
            Text(text = buildAnnotatedString {
                append(text)
                if (required)
                    withStyle(style = SpanStyle(color = Color.Red)) {
                        append(" *")
                    }
            })
        }
    }
}


//@Composable
//fun CardOptions(){
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceEvenly,
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        CardOptionPhone(true)
//        CardOptionTV()
//    }
//}


@Composable
@Preview
fun CardsKYC(){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            CardKYCComponents(text = "Frente", com.tablutech.modisdk.R.drawable.front_disable,com.tablutech.modisdk.R.drawable.front_filled, true, true){}
            CardKYCComponents(text = "Verso", com.tablutech.modisdk.R.drawable.back_disable, com.tablutech.modisdk.R.drawable.back_filled, true, true){}
        }
    }

}






