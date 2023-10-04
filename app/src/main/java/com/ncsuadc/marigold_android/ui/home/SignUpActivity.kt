package com.ncsuadc.marigold_android.ui.home

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
                    SignUpColumn(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize()/*.paint()...*/
                    )
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
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .padding(all = 22.dp)
            .then(modifier)
    ) {
        TitleText()
        Spacer(modifier = Modifier.padding(8.dp))
        CreateAccountText()
        Spacer(modifier = Modifier.padding(8.dp))
        SignUpForm()
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

@Composable
private fun TitleText(modifier: Modifier = Modifier) {
    Text(
        buildAnnotatedString {
            withStyle(
                SpanStyle(
                    brush = Brush.linearGradient(
                        // If we plan to use these colors more often we should make them a constant
                        // somewhere/add it to the theme. I'm not sure how to do this, however.
                        colors = listOf(Color(0xffffe501), Color(0xffffb320))
                    )
                )
            ) {
                append("Marigold ")
            }
            append("@\n")
            withStyle(SpanStyle(color = Color(0xffff3737))) {
                append("NC State University")
            }
        },
        style = SIGN_UP_TITLE_STYLE,
        modifier = modifier
    )
}

@Composable
private fun CreateAccountText(modifier: Modifier = Modifier) {
    Text(
        buildAnnotatedString {
            withStyle(SIGN_UP_TITLE_STYLE.toSpanStyle()) {
                append("Create an account.\n")
            }
            append("Already have an account? ")
            pushStringAnnotation(tag = "sign-in", annotation = "Sign up!")
            withStyle(
                SpanStyle(
                    color = Color(0xffffb320),
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("Sign In")
            }
            pop()

        },
        style = TextStyle(fontSize = 15.sp),
        modifier = modifier
    )
}

// I feel like there should be an easier way to get all children to stretch with the column.
// (Similar to CrossAxisAlignment.stretch in Flutter)
// https://api.flutter.dev/flutter/rendering/CrossAxisAlignment.html
@Composable
private fun SignUpForm(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        SignUpTextField(label = "E-Mail", value = email, keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next), onValueChange = { email = it })
        Spacer(modifier = Modifier.padding(8.dp))
        SignUpTextField(label = "Password", password = true, value = password, keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next), onValueChange = { password = it })
        Spacer(modifier = Modifier.padding(8.dp))
        // What's this one for?
        SignUpTextField(label = "Confirm ???", password = true, value = "", keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done),
            onValueChange = { /*TODO*/ })
        Spacer(modifier = Modifier.padding(8.dp))
        GradientButton(
            gradient = Brush.horizontalGradient(
                colors = listOf(Color(0xffffe501), Color(0xffffb320))
            ),
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { /*TODO*/ }

        ) {
            Text("Sign Up", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Sign Up",
                modifier = Modifier.width(24.dp))
        }
    }
}

// I took some liberties with the gradient button, but I think it looks better this way.
// Feel free to change it if you disagree.
@Composable
fun GradientButton(
    gradient : Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    content: @Composable () -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(),
        onClick = { onClick() },
    ) {
        Row(
            modifier = Modifier
                .background(gradient)
                .padding(horizontal = 20.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
    }
}

// Should probably put in a common file with SignInActivity
// when that's done so they can both use one
@Composable
private fun SignUpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    password: Boolean = false,
    label: String = "",
) {
    var passwordVisibility by remember { mutableStateOf(password) }

    TextField(
        value = value,
        visualTransformation = if (!passwordVisibility)
            VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = keyboardOptions,
        maxLines = 1,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        shape = MaterialTheme.shapes.medium,
        trailingIcon =  if (password) {
            {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                    // log output kt
                }, modifier = Modifier.padding(end = 8.dp)) {
                    Icon(
                        imageVector = if (passwordVisibility)
                            Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                        contentDescription = "Visibility",
                        tint = Color(0xffbfbfbf),
                    )
                }
            }
        } else null,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    )
}