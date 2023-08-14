package com.ncsuadc.marigold_android.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.lifecycle.lifecycleScope
import com.mongodb.app.presentation.login.EventSeverity
import com.mongodb.app.presentation.login.LoginAction
import com.mongodb.app.presentation.login.LoginEvent
import com.mongodb.app.presentation.login.LoginViewModel

import com.ncsuadc.marigold_android.app
import com.ncsuadc.marigold_android.ui.ui.theme.MarigoldAndroidTheme
import kotlinx.coroutines.launch

class ComposeLoginActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Fast-track task list screen if we are logged in
        if (app.currentUser != null) {
            Log.d("OWEN", "Time to head to the main screen!")
            finish()
            return
        }

        lifecycleScope.launch {
            // Subscribe to navigation and message-logging events
            loginViewModel.event
                .collect { event ->
                    when (event) {
                        is LoginEvent.GoToTasks -> {
                            event.process()
                            Log.d("OWEN", "Time to head to the main screen!")
//                            val intent = Intent(this@ComposeLoginActivity, ComposeItemActivity::class.java)
//                            startActivity(intent)
//                            finish()
                        }
                        is LoginEvent.ShowMessage -> event.process()
                    }
                }
        }

        setContent {
            MarigoldAndroidTheme {
                LoginScaffold(loginViewModel)
            }
        }
    }

    private fun LoginEvent.process() {
        when (severity) {
            EventSeverity.INFO ->
                Toast.makeText(this@ComposeLoginActivity, message, Toast.LENGTH_SHORT)
                    .show()
            EventSeverity.ERROR -> {
                Toast.makeText(this@ComposeLoginActivity, message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginActivityPreview() {
    MarigoldAndroidTheme {
        val viewModel = LoginViewModel().also {
            it.switchToAction(LoginAction.LOGIN)
            it.setEmail("test@test.com")
            it.setPassword("123456")
        }
        LoginScaffold(viewModel)
    }
}
