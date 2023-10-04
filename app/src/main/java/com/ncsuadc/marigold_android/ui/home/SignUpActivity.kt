package com.ncsuadc.marigold_android.ui.home

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
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


// Should this be in the theme or typography file?
val SIGN_UP_TITLE_STYLE = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 35.sp)

@Preview(showBackground = true)
@Composable
private fun TitleText(modifier: Modifier = Modifier) {
    Text(
        buildAnnotatedString {
            withStyle(
                SpanStyle(brush = Brush.linearGradient(
                // If we plan to use these colors more often we should make them a constant
                // somewhere/add it to the theme. I'm not sure how to do this, however.
                colors = listOf(Color(0xffffe501), Color(0xffffb320)))
            )) {
                append("Marigold ")
            }
            append("@\n")
            withStyle(SpanStyle(color = Color(0xffff3737))) {
                append("NC State University")
            }
        },
        style = SIGN_UP_TITLE_STYLE
    )
}
