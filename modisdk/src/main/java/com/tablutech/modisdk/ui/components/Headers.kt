package com.tablutech.modisdk.ui.components


import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tablutech.modisdk.*
import com.tablutech.modisdk.ui.themes.fontInfoColor
import com.tablutech.modisdk.ui.themes.montserratRegular



@Composable
fun TextHeaders(
    text: String = "Captura e Confirmação da sua\nIdentidade",
    modifier: Modifier = Modifier.fillMaxWidth().padding(top = 34.dp),
    fontFamily: FontFamily = montserratRegular,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight = FontWeight(400),
    fontSize: TextUnit = 14.sp,
    color: Color = Color(0xFF000000)
) {
    Text(
        text = text,
        fontFamily = montserratRegular,
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight(400),
        fontSize = 14.sp,
        color = Color(0xFF000000)
    )
}

fun pixelsToSp(context: Context, px: Float): Float {
    val scaledDensity = context.resources.displayMetrics.scaledDensity
    return px / scaledDensity
}

@Preview
@Composable
fun InfoCards(
    painter: Painter = painterResource(id = R.drawable.provadevida),
    textTitle: String = "Prova de vida",
    textDescription: String = "Com o telemóvel, siga as indicações do sistema para validar a fotografia do seu documento."){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painter,
            contentDescription = null )
        Spacer(modifier = Modifier.width(19.dp))

        Column {
            Text(text = textTitle, fontFamily = montserratRegular, fontSize = 12.sp)
            Text(text = textDescription, fontFamily = montserratRegular, color = fontInfoColor, fontSize = 11.sp)
        }
    }
}
