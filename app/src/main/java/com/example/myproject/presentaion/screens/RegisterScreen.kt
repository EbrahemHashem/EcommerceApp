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
import androidx.compose.material.icons.filled.Phone
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
import com.example.myproject.data.model.RegisterRequest
import com.example.myproject.util.CustomOutlinedTextField

class RegisterScreen : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val viewModel = MainViewModel()
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val phone = remember { mutableStateOf("") }
        val name = remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

        var emailError by remember { mutableStateOf("") }
        var passwordError by remember { mutableStateOf("") }
        var phoneError by remember { mutableStateOf("") }
        var nameError by remember { mutableStateOf("") }


        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.loginpage),
                contentDescription = null,
                Modifier
                    .width(250.dp)
                    .height(250.dp)
            )
            Text(
                text = "Register",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp,
                ),
            )
            Spacer(modifier = Modifier.height(5.dp))

            CustomOutlinedTextField(email.value, onValueChange = { email.value = it
                emailError = ""}, label = "Email", leading = Icons.Default.Email)

            if (emailError.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 4.dp, start = 35.dp)
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

            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it
                    passwordError = ""},
                label = { Text("Password", fontSize = 16.sp) },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisible = !passwordVisible
                        },
                    ) {
                        Icon(
                            painter = if (passwordVisible) painterResource(R.drawable.eye) else
                                painterResource(R.drawable.eyeoff), contentDescription = "",
                            tint = if (passwordVisible) Color.Black else Color.Gray
                        )
                    }
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = null )
                },
                visualTransformation = if (passwordVisible)
                    VisualTransformation.None else PasswordVisualTransformation(),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 18.sp,
                    color = Color.Black
                ) // Change the text style

            )

            if (passwordError.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 4.dp, start = 35.dp)
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
            Spacer(modifier = Modifier.height(5.dp))

            CustomOutlinedTextField(phone.value, onValueChange = { phone.value = it
                phoneError = ""}, "Phone",leading = Icons.Default.Phone)

            if (phoneError.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 4.dp, start = 35.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = phoneError,
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            CustomOutlinedTextField(name.value, onValueChange = { name.value = it
                nameError = ""}, "Name", leading = Icons.Default.Person)

            if (nameError.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 4.dp, start = 35.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = nameError,
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
                    // Validation
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
                        emailError = "Please enter a valid email address"
                    } else if (password.value.length < 6) {
                        passwordError = "Password must be at least 6 characters"
                    } else if (!phone.value.matches(Regex("^\\+?\\d{10,13}\$"))) {
                        phoneError = "Please enter a valid phone number"
                    } else if (name.value.isEmpty()) {
                        nameError = "Name cannot be empty"
                    } else {
                        // Proceed with registration if validation passes
                        viewModel.register(
                            RegisterRequest(
                                email = email.value,
                                password = password.value,
                                phone = phone.value,
                                name = name.value
                            )
                        )
                        navigator.pop()
                    }}) {
                Text(
                    text = "SignUp"
                )
            }
            TextButton(onClick = { navigator.pop() }) {
                Text(text = "already have an account? Login", fontSize = 15.sp, color = Color.Gray)
            }

        }
    }

}

