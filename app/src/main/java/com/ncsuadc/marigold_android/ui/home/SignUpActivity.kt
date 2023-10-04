package com.ncsuadc.marigold_android.ui.home

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncsuadc.marigold_android.ui.theme.MarigoldTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarigoldTheme {
                Surface {
                    SignUpColumn(modifier = Modifier.fillMaxSize()/*.paint()...*/)
                }
//                Box(modifier = Modifier
//                    .fillMaxSize()
//                    .paint(
//                        painterResource(id = R.drawable.img),
//                        contentScale = ContentScale.FillBounds
//                    )) {
//
//                }
            }
        }
    }
}

@Composable
private fun SignUpColumn(modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.Bottom, modifier = modifier.padding(all = 16.dp)) {
        TitleText()
        Text("2")
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignUpColumnPreview() {
    MarigoldTheme {
        Surface {
            SignUpColumn(modifier = Modifier.fillMaxSize())
        }
    }
}

// get color from hex kotlin
// https://stackoverflow.com/questions/56713791/how-to-convert-hex-string-to-color-in-jetpack-compose


// Is there a way to assign a text style to all children in a Composable?
// In Flutter the equivalent would be RichText, which has a text style as well as all its children.
// https://api.flutter.dev/flutter/widgets/RichText-class.html
@Preview(showBackground = true)
@Composable
private fun TitleText(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row {
            Text("Marigold ", style = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
                brush = Brush.linearGradient(
                    // If we plan to use these colors more often we should make them a constant
                    // somewhere/add it to the theme. I'm not sure how to do this, however.
                    colors = listOf(Color(0xffffe501), Color(0xffffb320)))
            ))
            Text("@", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp,)
        }
        Text("NC State University", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp, color = Color(0xffff3737))
    }
}
