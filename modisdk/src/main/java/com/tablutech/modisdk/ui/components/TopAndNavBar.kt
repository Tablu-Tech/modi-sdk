package com.tablutech.modisdk.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tablutech.modisdk.ui.themes.ButtonOutlineColor
import com.tablutech.modisdk.ui.themes.GreenModi
import com.tablutech.modisdk.ui.themes.montserratRegular

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TopAppBarCustom(label: String = "Captura de documentos") {
    TopAppBar(title = {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = label,
                fontSize = 18.sp,
                fontFamily = montserratRegular,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
        }

    }, modifier = Modifier.shadow(1.dp))
}


@Composable
fun ButtonFilled(
    enabled: Boolean = true, text: String = "Click",
    modifier: Modifier = Modifier
        .width(155.dp)
        .height(45.dp)
        .clip(RoundedCornerShape(6.dp)),
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        shape = RectangleShape,
        enabled = enabled,
        modifier = modifier.background(color = GreenModi)
    ) {
        Text(text = text)
    }
}


@Composable
fun ButtonOutlined(
    enabled: Boolean = true,
    text: String = "Click",
    onClick: () -> Unit,
    fontSize: TextUnit = 16.sp,
    modifier: Modifier = Modifier
        .width(155.dp)
        .height(45.dp)
        .border(
            width = 1.dp,
            color = ButtonOutlineColor,
            shape = RoundedCornerShape(6.dp)
        )
) {
    Button(
        onClick = onClick,
        shape = RectangleShape,
        enabled = enabled,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White.copy(alpha = 0f),
        )
    ) {
        Text(text = text, color = GreenModi, fontSize = fontSize)
    }
}

@Preview
@Composable
fun BottomBar(
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    navigationBack: () -> Unit = {},
    navigation: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        ButtonOutlined(
            text = "Voltar",
            onClick = navigationBack,
        )
        Spacer(modifier = Modifier.width(16.dp))
        ButtonFilled(
            text = "Proximo",
            onClick = navigation,
            enabled = enabled
        )
    }
}

@Preview
@Composable
fun BottomBarCancel(
    modifier: Modifier = Modifier,
    navigationBack: () -> Unit = {},
    navigation: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        ButtonOutlined(
            text = "Cancelar",
            onClick = navigationBack,
            modifier = Modifier
                .fillMaxWidth()
                .width(155.dp)
                .height(45.dp)
                .border(
                    width = 1.dp,
                    color = ButtonOutlineColor,
                    shape = RoundedCornerShape(6.dp)
                )
        )
    }
}






