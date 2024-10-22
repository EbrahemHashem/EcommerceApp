package com.example.myproject.presentaion.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.myproject.presentaion.viewmodel.MainViewModel
import com.example.myproject.R
import com.example.myproject.data.model.LoginRequest
import com.example.myproject.util.CustomOutlinedTextField


class LoginScreen : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val viewModel = MainViewModel()
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        var emailError by remember { mutableStateOf("") }
        var passwordError by remember { mutableStateOf("") }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.loginpage),
                contentDescription = null,
                Modifier
                    .width(300.dp)
                    .height(300.dp)
            )
            Text(
                text = "Login",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp,
                ),
                modifier = Modifier.padding(top = 10.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            CustomOutlinedTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                    emailError = ""
                },
                label = "Email",
                leading = Icons.Default.Email
            )
            if (emailError.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 4.dp, start = 20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = emailError,
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                    passwordError = ""
                },
                label = { Text("Password", fontSize = 16.sp) },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = if (passwordVisible) painterResource(R.drawable.eye) else painterResource(R.drawable.eyeoff),
                            contentDescription = null,
                            tint = if (passwordVisible) Color.Black else Color.Gray
                        )
                    }
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = null)
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                textStyle = LocalTextStyle.current.copy(fontSize = 18.sp, color = Color.Black)
            )
            if (passwordError.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 4.dp, start = 20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = passwordError,
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Button(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .width(250.dp)
                    .height(50.dp),
                onClick = {
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
                        emailError = "Please enter a valid email address"
                    } else if (password.value.isEmpty()) {
                        passwordError = "Password cannot be empty"
                    } else {
                        viewModel.login(
                            LoginRequest(email = email.value, password = password.value),
                            onSuccess = {
                                navigator.push(MainScreen(it))
                            }
                        )
                    }
                }
            ) {
                Text(text = "Login")
            }

            TextButton(onClick = { navigator.push(RegisterScreen()) }) {
                Text(
                    text = "Don't have an account? Sign Up",
                    fontSize = 15.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

