package com.ncsuadc.marigold_android.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncsuadc.marigold_android.R

//Font family value for the "Ceviche One" font in resources
val cevicheFamily = FontFamily(
    Font(R.font.ceviche_one, FontWeight.Normal)
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopAppBar() {
    //CenterAlignedTopApp bar component holds the content of the component
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            //Color is the Marigold yellow theme
            titleContentColor = Color(0xffffb320),
        ),
        title = {
            Text(
                "Marigold",
                fontFamily = cevicheFamily,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 48.sp

            )
        },
        //Profile picture icon button, click functionality currently unimplemented
        actions = {
            IconButton(
                onClick = { /* do something */ },
                //Modifier controlling distance of the pic from the right side of the bar
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Image(
                    //Painter to hold the drawable, current "xavier_pfp" in resources as placeholder
                    painter = painterResource(R.drawable.xavier_pfp),
                    contentDescription = "Localized description",
                    //Modifier changing the size of the picture
                    modifier = Modifier
                        .height(48.dp)
                        .width(48.dp)

                )
            }
        }
    )
}


