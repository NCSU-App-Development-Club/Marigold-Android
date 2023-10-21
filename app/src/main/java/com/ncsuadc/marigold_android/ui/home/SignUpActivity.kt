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
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
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
import androidx.compose.ui.platform.LocalContext
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
import com.ncsuadc.marigold_android.ui.home.shared.GradientButton
import com.ncsuadc.marigold_android.ui.home.shared.InvalidBanner
import com.ncsuadc.marigold_android.ui.home.shared.SIGN_UP_TITLE_STYLE
import com.ncsuadc.marigold_android.ui.home.shared.TitleText
import com.ncsuadc.marigold_android.ui.theme.MarigoldTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarigoldTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background


                ) {
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
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background

        ) {
            SignUpColumn(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun CreateAccountText(modifier: Modifier = Modifier) {
    Text(
        buildAnnotatedString {
            withStyle(SIGN_UP_TITLE_STYLE.toSpanStyle()) {
                append("Create an account.\n")
            }
            append("Already have an account? ")
            pushStringAnnotation(tag = "sign-in", annotation = "sign-in")
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
        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold),
        modifier = modifier
    )
}

// I feel like there should be an easier way to get all children to stretch with the column.
// (Similar to CrossAxisAlignment.stretch in Flutter)
// https://api.flutter.dev/flutter/rendering/CrossAxisAlignment.html
enum class EmailValidation(val description: String? = null) {
    EMPTY("Please enter an e-mail"),
    NOT_NC_STATE("Please enter a valid NC State e-mail"),
    VALID
}
enum class PasswordValidation(val description: String? = null) {
    EMPTY("Please enter a password"),
    VALID
}
enum class ConfirmPasswordValidation(val description: String? = null) {
    EMPTY("Please confirm your password"),
    DOES_NOT_MATCH("Passwords do not match"),
    VALID
}

@Composable
private fun SignUpForm(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // switch to validating while typing if user failed once.
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

    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordValid by remember { mutableStateOf(ConfirmPasswordValidation.VALID) }
    val confirmPasswordValidation = {
        confirmPasswordValid = if (confirmPassword.isBlank()) {
            ConfirmPasswordValidation.EMPTY
        } else if (confirmPassword != password) {
            ConfirmPasswordValidation.DOES_NOT_MATCH
        } else {
            ConfirmPasswordValidation.VALID
        }

        confirmPasswordValid == ConfirmPasswordValidation.VALID
    }


    val allValid = {
        val emailValidated = emailValidation()
        val passwordValidated = passwordValidation()
        val confirmPasswordValidated = confirmPasswordValidation()
        emailValidated && passwordValidated && confirmPasswordValidated
    }

    Column(modifier = modifier) {
        if (emailValid != EmailValidation.VALID) InvalidBanner(emailValid.description)
        SignUpTextField(
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
        SignUpTextField(
            label = "Password",
            password = true, value = password,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
            onValueChange = {
                password = it
                if (validationFailed) passwordValidation()
            },
            isError = passwordValid != PasswordValidation.VALID
        )
        if (confirmPasswordValid != ConfirmPasswordValidation.VALID) InvalidBanner(confirmPasswordValid.description)
        else Spacer(modifier = Modifier.padding(8.dp))
        SignUpTextField(label = "Confirm password",
            password = true,
            value = confirmPassword,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
            onValueChange = {
                confirmPassword = it
                if (validationFailed) confirmPasswordValidation()
            },
            isError = confirmPasswordValid != ConfirmPasswordValidation.VALID
        )
        Spacer(modifier = Modifier.padding(8.dp))
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
            Text("Sign Up", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Sign Up",
                modifier = Modifier.width(24.dp))
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
    isError : Boolean = false,
    password: Boolean = false,
    label: String = "",
) {
    var passwordVisibility by remember { mutableStateOf(password) }

    TextField(
        value = value,
        isError = isError,
        visualTransformation = if (!passwordVisibility)
            VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = keyboardOptions,
        singleLine = true,
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
            errorIndicatorColor = Color.Transparent,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignUpTextFieldPreview() {
    var silly by remember { mutableStateOf("") }

    MarigoldTheme {
        Surface(
            color = MaterialTheme.colorScheme.background

        ) {
            SignUpTextField(
                label = "E-Mail",
                value = silly,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                onValueChange = { silly = it }
            )
        }
    }
}