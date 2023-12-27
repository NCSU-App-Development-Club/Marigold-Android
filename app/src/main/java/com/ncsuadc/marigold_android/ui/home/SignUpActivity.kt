package com.ncsuadc.marigold_android.ui.home

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.ncsuadc.marigold_android.ATLAS_APP_ID
import com.ncsuadc.marigold_android.ui.home.shared.GradientButton
import com.ncsuadc.marigold_android.ui.home.shared.InvalidBanner
import com.ncsuadc.marigold_android.ui.home.shared.SIGN_UP_TITLE_STYLE
import com.ncsuadc.marigold_android.ui.home.shared.TitleText
import com.ncsuadc.marigold_android.ui.theme.MarigoldTheme
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.exceptions.BadRequestException
import io.realm.kotlin.mongodb.exceptions.ConnectionException
import io.realm.kotlin.mongodb.exceptions.UserAlreadyExistsException
import kotlinx.coroutines.launch

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
                            .fillMaxSize()
                    )
                }
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

enum class EmailValidationState(val description: String? = null) {
    EMPTY("Please enter an e-mail"),
    NOT_NC_STATE("Please enter a valid NC State e-mail"),
    EMAIL_EXISTS("An account with this e-mail already exists"),
    VALID
}
enum class PasswordValidationState(val description: String? = null) {
    EMPTY("Please enter a password"),
    SHORT("Password must be at least 6 characters"),
    VALID
}
enum class ConfirmPasswordValidationState(val description: String? = null) {
    EMPTY("Please confirm your password"),
    DOES_NOT_MATCH("Passwords do not match"),
    VALID
}
enum class SigningUpState {
    INITIAL,
    SIGNING_UP,
    FAILED
}
@Composable
private fun SignUpForm(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var signingUpState by remember { mutableStateOf(SigningUpState.INITIAL) }

    var email by remember { mutableStateOf("") }
    var emailValid by remember { mutableStateOf(EmailValidationState.VALID) }
    val emailValidationState = {
        emailValid = if (email.isBlank()) {
            EmailValidationState.EMPTY
        } else if (!email.endsWith("@ncsu.edu") || email.startsWith("@ncsu.edu")) {
            EmailValidationState.NOT_NC_STATE
        } else {
            EmailValidationState.VALID
        }

        emailValid == EmailValidationState.VALID
    }

    var password by remember { mutableStateOf("") }
    var passwordValid by remember { mutableStateOf(PasswordValidationState.VALID) }
    val passwordValidationState = {
        passwordValid = if (password.isBlank()) {
            PasswordValidationState.EMPTY
        } else {
            PasswordValidationState.VALID
        }

        passwordValid == PasswordValidationState.VALID
    }

    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordValid by remember { mutableStateOf(ConfirmPasswordValidationState.VALID) }
    val confirmPasswordValidationState = {
        confirmPasswordValid = if (confirmPassword.isBlank()) {
            ConfirmPasswordValidationState.EMPTY
        } else if (confirmPassword != password) {
            ConfirmPasswordValidationState.DOES_NOT_MATCH
        } else {
            ConfirmPasswordValidationState.VALID
        }

        confirmPasswordValid == ConfirmPasswordValidationState.VALID
    }


    val allValid = {
        val emailValidated = emailValidationState()
        val passwordValidated = passwordValidationState()
        val confirmPasswordValidated = confirmPasswordValidationState()
        emailValidated && passwordValidated && confirmPasswordValidated
    }

    Column(modifier = modifier) {
        if (emailValid != EmailValidationState.VALID) InvalidBanner(emailValid.description)
        SignUpTextField(
            label = "E-Mail",
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
            onValueChange = {
                email = it
                if (signingUpState == SigningUpState.FAILED) emailValidationState()
            },
            isError = emailValid != EmailValidationState.VALID
        )
        if (passwordValid != PasswordValidationState.VALID) InvalidBanner(passwordValid.description)
        else Spacer(modifier = Modifier.padding(8.dp))
        SignUpTextField(
            label = "Password",
            password = true, value = password,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
            onValueChange = {
                password = it
                if (signingUpState == SigningUpState.FAILED) passwordValidationState()
            },
            isError = passwordValid != PasswordValidationState.VALID
        )
        if (confirmPasswordValid != ConfirmPasswordValidationState.VALID) InvalidBanner(confirmPasswordValid.description)
        else Spacer(modifier = Modifier.padding(8.dp))
        SignUpTextField(label = "Confirm password",
            password = true,
            value = confirmPassword,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
            onValueChange = {
                confirmPassword = it
                if (signingUpState == SigningUpState.FAILED) confirmPasswordValidationState()
            },
            isError = confirmPasswordValid != ConfirmPasswordValidationState.VALID
        )
        Spacer(modifier = Modifier.padding(8.dp))

        val coroutineScope = rememberCoroutineScope()
        suspend fun signUp(email: String, password: String) {
            val app = App.Companion.create(ATLAS_APP_ID)
            app.emailPasswordAuth.registerUser(email, password)
        }

        val alpha = when (signingUpState) {
            SigningUpState.SIGNING_UP -> 0.1f
            else -> 1f
        }
        GradientButton(
            gradient = Brush.horizontalGradient(
                colors = listOf(Color(0xffffe501).copy(alpha), Color(0xffffb320).copy(alpha))
            ),
            modifier = Modifier
                .fillMaxWidth(),
            enabled = signingUpState != SigningUpState.SIGNING_UP,
            onClick = {
                signingUpState = SigningUpState.SIGNING_UP
                if (allValid()) {
                    // sign up
                    coroutineScope.launch {
                        try {
                            signUp(email, password)
                        } catch (e: UserAlreadyExistsException) {
                            emailValid = EmailValidationState.EMAIL_EXISTS
                            signingUpState = SigningUpState.FAILED
                            return@launch
                        } catch (e: BadRequestException) {
                            passwordValid = PasswordValidationState.SHORT
                            signingUpState = SigningUpState.FAILED
                            return@launch
                        } catch (e: ConnectionException) {
                            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
                            signingUpState = SigningUpState.FAILED
                            return@launch
                        } catch (e: Exception) {
                            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                            signingUpState = SigningUpState.FAILED
                            return@launch
                        }

                        Toast.makeText(context, "User added", Toast.LENGTH_SHORT).show()
                        // go to other screen
                        context.startActivity(Intent(context, VerifyEmailSignUpActivity::class.java))
                        signingUpState = SigningUpState.INITIAL
                    }
                } else if (signingUpState != SigningUpState.FAILED) {
                    signingUpState = SigningUpState.FAILED
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