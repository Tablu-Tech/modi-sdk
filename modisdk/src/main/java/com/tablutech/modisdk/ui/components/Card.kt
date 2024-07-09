package com.tablutech.modisdk.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tablutech.modisdk.ui.themes.BlueDark
import com.tablutech.modisdk.ui.themes.ButtonOutlineColor
import com.tablutech.modisdk.*




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
            CardKYCComponents(text = "Frente", R.drawable.front_disable,
                R.drawable.front_filled, true, true){}
            CardKYCComponents(text = "Verso", R.drawable.back_disable, R.drawable.back_filled, true, true){}
        }
    }

}






