package com.ncsuadc.marigold_android.ui.home

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.ncsuadc.marigold_android.ui.home.shared.GradientButton
import com.ncsuadc.marigold_android.ui.home.shared.InvalidBanner
import com.ncsuadc.marigold_android.ui.home.shared.SIGN_UP_TITLE_STYLE
import com.ncsuadc.marigold_android.ui.home.shared.TitleText
import com.ncsuadc.marigold_android.ui.theme.MarigoldTheme

class VerifyEmailSignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarigoldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VerifySignUpColumn(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
private fun VerifySignUpColumn(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .padding(all = 22.dp)
            .then(modifier)
    ) {
        TitleText()
        Spacer(modifier = Modifier.padding(8.dp))
        VerifyAccountText()
        Spacer(modifier = Modifier.padding(8.dp))
        VerifyForm()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerifySignUpColumnPreview() {
    MarigoldTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            VerifySignUpColumn(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun VerifyAccountText(modifier: Modifier = Modifier) {
    val text = buildAnnotatedString {
        withStyle(SIGN_UP_TITLE_STYLE.toSpanStyle()) {
            append("Verify your email.\n")
        }
        pushStringAnnotation(tag = "back", annotation = "Go back")
        withStyle(
            SpanStyle(
                color = Color(0xffffb320),
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("Go back")
        }
        pop()

    }

    val color = MaterialTheme.colorScheme.onBackground
    val context = LocalContext.current as? Activity

    ClickableText(
        text,
        onClick = { offset ->
            val annotations = text.getStringAnnotations(
                tag = "back", start = offset, end = offset
            )
            annotations.firstOrNull()?.let {
                context?.finish()
            }

        },
        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold, color = color),
        modifier = modifier
    )
}

@Composable
fun VerifyForm(modifier: Modifier = Modifier) {
    var digit1 by remember { mutableStateOf("") }
    var digit2 by remember { mutableStateOf("") }
    var digit3 by remember { mutableStateOf("") }
    var digit4 by remember { mutableStateOf("") }

    var valid by remember { mutableStateOf(true) }
    val validation = {
        valid =
            digit1.isNotEmpty() && digit2.isNotEmpty() && digit3.isNotEmpty() && digit4.isNotEmpty()
        valid
    }

    val localFocusManager = LocalFocusManager.current
    val context = LocalContext.current

    Column(modifier = modifier) {
        Text(
            "We sent a verification code to your email. Please input the code below.",
            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold)
        )
        if (!valid) InvalidBanner(text = "Please enter a valid verification code.") else Spacer(
            modifier = Modifier.padding(8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            VerifyCodeTextField(
                value = digit1,
                onValueChange = {
                    if (it.length <= 1 && it.isDigitsOnly()) {
                        digit1 = it
                    }

                    if (it.length == 1) {
                        localFocusManager.moveFocus(FocusDirection.Next)
                    } else if (it.isEmpty()) {
                        localFocusManager.clearFocus()
                    } else if (it.length == 4) {
                        // if the user pastes a 4 digit code, we want to split it up
                        digit1 = it[0].toString()
                        digit2 = it[1].toString()
                        digit3 = it[2].toString()
                        digit4 = it[3].toString()
                        localFocusManager.clearFocus()
                    }
                },
            )
            VerifyCodeTextField(
                value = digit2,
                onValueChange = {
                    if (it.length <= 1 && it.isDigitsOnly()) {
                        digit2 = it
                    }

                    if (it.length == 1) {
                        localFocusManager.moveFocus(FocusDirection.Next)
                    } else {
                        localFocusManager.moveFocus(FocusDirection.Previous)
                    }
                },
            )
            VerifyCodeTextField(
                value = digit3,
                onValueChange = {
                    if (it.length <= 1 && it.isDigitsOnly()) {
                        digit3 = it
                    }

                    if (it.length == 1) {
                        localFocusManager.moveFocus(FocusDirection.Next)
                    } else {
                        localFocusManager.moveFocus(FocusDirection.Previous)
                    }
                },
            )
            VerifyCodeTextField(
                value = digit4,
                imeAction = ImeAction.Done,
                onValueChange = {
                    if (it.length <= 1 && it.isDigitsOnly()) {
                        digit4 = it
                    }

                    if (it.length == 1) {
                        localFocusManager.clearFocus()
                    } else {
                        localFocusManager.moveFocus(FocusDirection.Previous)
                    }
                },
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            buildAnnotatedString {
                append("Didn't receive an email? ")
                pushStringAnnotation(tag = "resend", annotation = "Resend Email")
                withStyle(
                    SpanStyle(
                        color = Color(0xffffb320),
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Resend")
                }
                pop()

            },
            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold),
            modifier = modifier
        )
        Spacer(modifier = Modifier.padding(8.dp))
        GradientButton(
            gradient = Brush.horizontalGradient(
                colors = listOf(Color(0xffffe501), Color(0xffffb320))
            ),
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                if (validation()) {
                    val verify = {
                        (digit1 + digit2 + digit3 + digit4).toInt()
                        Log.d("emailtest", "todo send to mongo");

                        true
                    }
                    if (verify()) {
                        val toast = Toast.makeText(
                            context,
                            "User successfully added and verified",
                            Toast.LENGTH_LONG
                        )
                        toast.show()
                        //todo send the user somewhere after success - home screen maybe?
                    } else {
                        val toast =
                            Toast.makeText(context, "User could not be verified", Toast.LENGTH_LONG)
                        toast.show()
                    }
                }
            }

        ) {
            Text("Verify Email", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Verify Email",
                modifier = Modifier.width(24.dp)
            )
        }
    }
}

@Composable
private fun VerifyCodeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next,
    label: String = "",
) {
    TextField(
        value = value,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = KeyboardType.Number
        ),
        singleLine = true,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center, fontSize = 30.sp,
            baselineShift = BaselineShift(0.6f)
        ),
        modifier = Modifier
            .requiredWidth(75.dp)

            .then(modifier)
    )
}