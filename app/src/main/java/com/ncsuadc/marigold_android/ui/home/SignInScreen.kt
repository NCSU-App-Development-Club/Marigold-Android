package com.ncsuadc.marigold_android.ui.home

//import android.content.Intent
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.platform.LocalContext
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
//import androidx.compose.ui.platform.LocalContext
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
        Spacer(modifier = Modifier.padding(17.dp))
        SignInText()
        Spacer(modifier = Modifier.padding(12.dp))
        SignInForm()


    }
}


@Composable
private fun SignInText(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val greeting = buildAnnotatedString {
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
            append("Sign Up ")
        }
        pop()

    }
    //Clickable text that only responds to clicks on the 37th character (start of "Sign Up" button
    ClickableText(
        onClick = { offset ->
            if(offset in 37..44){
                context.startActivity(Intent(context, SignUpActivity::class.java))
            }
        },
        text= greeting,
        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold),
        modifier = modifier
    )

}




@Composable
private fun SignInForm(modifier: Modifier = Modifier) {

  //  val context = LocalContext.current

    var validationFailed by remember { mutableStateOf(false) }

    var email by remember { mutableStateOf("") }
    var emailValid by remember { mutableStateOf(EmailValidationState.VALID) }
    val emailValidation = {
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
    val passwordValidation = {
        passwordValid = if (password.isBlank()) {
            PasswordValidationState.EMPTY
        } else {
            PasswordValidationState.VALID
        }

        passwordValid == PasswordValidationState.VALID
    }


    val allValid = {
        val emailValidated = emailValidation()
        val passwordValidated = passwordValidation()

        emailValidated && passwordValidated
    }

    Column(modifier = modifier) {
        if (emailValid != EmailValidationState.VALID) InvalidBanner(emailValid.description)
        OnboardingTextField(
            label = "E-Mail",
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
            onValueChange = {
                email = it
                if (validationFailed) emailValidation()
            },
            isError = emailValid != EmailValidationState.VALID
        )
        if (passwordValid != PasswordValidationState.VALID) InvalidBanner(passwordValid.description)
        else Spacer(modifier = Modifier.padding(8.dp))
        OnboardingTextField(
            label = "Password",
            password = true, value = password,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
            onValueChange = {
                password = it
                if (validationFailed) passwordValidation()
            },
            isError = passwordValid != PasswordValidationState.VALID
        )

        Spacer(modifier = Modifier.padding(4.dp))

        //Text with flow to forgot password screen
        Row {
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

        Spacer(modifier = Modifier.padding(14.dp)) //Bigger space for aesthetics, change? TODO
        GradientButton(
            gradient = Brush.horizontalGradient(
                colors = listOf(Color(0xffffe501), Color(0xffffb320))
            ),
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                if (allValid()) {
                    // go to other screen
//                    context.startActivity(Intent(context, VerifyEmailSignUpActivity::class.java))
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







