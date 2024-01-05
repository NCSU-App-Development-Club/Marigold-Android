package com.ncsuadc.marigold_android.ui.home

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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

import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.ncsuadc.marigold_android.ui.home.shared.GradientButton
import com.ncsuadc.marigold_android.ui.home.shared.SIGN_UP_TITLE_STYLE
import com.ncsuadc.marigold_android.ui.home.shared.TitleText
import com.ncsuadc.marigold_android.ui.theme.MarigoldTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.text.style.TextAlign
import com.ncsuadc.marigold_android.ui.home.shared.InvalidBanner
import com.ncsuadc.marigold_android.ui.home.shared.OnboardingTextField


class SignInScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarigoldTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignInColumn(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignInColumnPreview() {
    MarigoldTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background

        ) {
            SignInColumn(modifier = Modifier.fillMaxSize())
        }
    }
}





@Composable
private fun SignInColumn(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .padding(all = 22.dp)
            .then(modifier)
    ) {

        TitleText()
        Spacer(modifier = Modifier.padding(15.dp))
        SignInText()
        Spacer(modifier = Modifier.padding(12.dp))
        SignInForm()


    }
}


@Composable
private fun SignInText(modifier: Modifier = Modifier) {

    Text(
        buildAnnotatedString {
            withStyle(SIGN_UP_TITLE_STYLE.toSpanStyle()) {
                append("Welcome Back!\n")
            }
            append("Don't have an account? ")
            pushStringAnnotation(tag = "sign-up", annotation = "sign-up")
            withStyle(
                SpanStyle(
                    color = Color(0xffffb320),
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("Sign Up")
            }
            pop()

        },
        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold),
        modifier = modifier
    )

}




@Composable
private fun SignInForm(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    var validationFailed by remember { mutableStateOf(false) }

    var email by remember { mutableStateOf("") }
    var emailValid by remember { mutableStateOf(EmailValidation.VALID) }
    val emailValidation = {
        emailValid = if (email.isBlank()) {
            EmailValidation.EMPTY
        } else if (!email.endsWith("@ncsu.edu") || email.startsWith("@ncsu.edu")) {
            EmailValidation.NOT_NC_STATE
        } else {
            EmailValidation.VALID
        }

        emailValid == EmailValidation.VALID
    }

    var password by remember { mutableStateOf("") }
    var passwordValid by remember { mutableStateOf(PasswordValidation.VALID) }
    val passwordValidation = {
        passwordValid = if (password.isBlank()) {
            PasswordValidation.EMPTY
        } else {
            PasswordValidation.VALID
        }

        passwordValid == PasswordValidation.VALID
    }


    val allValid = {
        val emailValidated = emailValidation()
        val passwordValidated = passwordValidation()

        emailValidated && passwordValidated
    }

    Column(modifier = modifier) {
        if (emailValid != EmailValidation.VALID) InvalidBanner(emailValid.description)
        OnboardingTextField(
            label = "E-Mail",
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
            onValueChange = {
                email = it
                if (validationFailed) emailValidation()
            },
            isError = emailValid != EmailValidation.VALID
        )
        if (passwordValid != PasswordValidation.VALID) InvalidBanner(passwordValid.description)
        else Spacer(modifier = Modifier.padding(8.dp))
        OnboardingTextField(
            label = "Password",
            password = true, value = password,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
            onValueChange = {
                password = it
                if (validationFailed) passwordValidation()
            },
            isError = passwordValid != PasswordValidation.VALID
        )

        Spacer(modifier = Modifier.padding(4.dp))

        //Text with flow to forgot password screen
        Row() {
            Text(
                "Forgot Password?",
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    color = Color(0xffffb320),
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.padding(12.dp)) //Bigger space for aesthetics, change? TODO
        GradientButton(
            gradient = Brush.horizontalGradient(
                colors = listOf(Color(0xffffe501), Color(0xffffb320))
            ),
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                if (allValid()) {
                    // go to other screen
                    context.startActivity(Intent(context, VerifyEmailSignUpActivity::class.java))
                } else if (!validationFailed) {
                    validationFailed = true
                }
            }

        ) {
            Text("Sign In", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Sign In",
                modifier = Modifier.width(24.dp))
        }






    }

}

//@Composable
//private fun SignUpTextField(
//    value: String,
//    onValueChange: (String) -> Unit,
//    keyboardOptions: KeyboardOptions,
//    modifier: Modifier = Modifier,
//    isError : Boolean = false,
//    password: Boolean = false,
//    label: String = "",
//) {
//    var passwordVisibility by remember { mutableStateOf(password) }
//
//    androidx.compose.material3.TextField(
//        value = value,
//        isError = isError,
//        visualTransformation = if (!passwordVisibility)
//            VisualTransformation.None else PasswordVisualTransformation(),
//        keyboardOptions = keyboardOptions,
//        singleLine = true,
//        onValueChange = onValueChange,
//        label = { Text(text = label) },
//        shape = MaterialTheme.shapes.medium,
//        trailingIcon = if (password) {
//            {
//                IconButton(onClick = {
//                    passwordVisibility = !passwordVisibility
//                    // log output kt
//                }, modifier = Modifier.padding(end = 8.dp)) {
//                    Icon(
//                        imageVector = if (passwordVisibility)
//                            Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
//                        contentDescription = "Visibility",
//                        tint = Color(0xffbfbfbf),
//                    )
//                }
//            }
//        } else null,
//        colors = TextFieldDefaults.colors(
//            focusedIndicatorColor = Color.Transparent,
//            unfocusedIndicatorColor = Color.Transparent,
//            disabledIndicatorColor = Color.Transparent,
//            errorIndicatorColor = Color.Transparent,
//        ),
//        modifier = Modifier
//            .fillMaxWidth()
//            .then(modifier)
//    )
//}





