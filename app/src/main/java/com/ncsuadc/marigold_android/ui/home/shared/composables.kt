package com.ncsuadc.marigold_android.ui.home.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Should this be in the theme or typography file?
val SIGN_UP_TITLE_STYLE = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 35.sp)

@Composable
fun TitleText(modifier: Modifier = Modifier) {
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

// I took some liberties with the gradient button, but I think it looks better this way.
// Feel free to change it if you disagree.
@Composable
fun GradientButton(
    gradient : Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(),
        enabled = enabled,
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

@Composable
fun InvalidBanner(text: String?, modifier: Modifier = Modifier) {
    Row(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp).then(modifier), verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Filled.Error, contentDescription = "Error", tint = MaterialTheme.colorScheme.error)
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text ?: "", color = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun OnboardingTextField(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    isError : Boolean = false,
    password: Boolean = false,
    label: String = "",
) {
    var passwordVisibility by remember { mutableStateOf(password) }

    androidx.compose.material3.TextField(
        value = value,
        isError = isError,
        visualTransformation = if (!passwordVisibility)
            VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = keyboardOptions,
        singleLine = true,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        shape = MaterialTheme.shapes.medium,
        trailingIcon = if (password) {
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