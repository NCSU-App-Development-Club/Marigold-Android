package com.ncsuadc.marigold_android.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.ncsuadc.marigold_android.R


val cevicheFamily = FontFamily(
    Font(R.font.ceviche_one, FontWeight.Normal)
)

//val provider = GoogleFont.Provider(
//    providerAuthority = "com.google.android.gms.fonts",
//    providerPackage = "com.google.android.gms",
//    certificates = R.array.com_google_android_gms_fonts_certs
//)
//
//val fontName = GoogleFont("Ceviche One")
//
//val fontFamily = FontFamily(
//    Font(googleFont = fontName, fontProvider = provider)
//)

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopAppBar() {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color(0xffffb320),
                ),
                title = {
                    Text(
                        "Marigold",
                        fontFamily = cevicheFamily,
                        fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,

                    )
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Rounded.Image,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )

}


