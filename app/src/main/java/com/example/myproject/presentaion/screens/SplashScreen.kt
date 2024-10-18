package com.example.myproject.presentaion.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.example.myproject.R
import kotlinx.coroutines.delay


class SplashScreen : Screen {
    @Composable
    override fun Content() {
        var visible by remember { mutableStateOf(true) }
        val offsetY = remember { Animatable(500f) }
        val textOpacity = remember { Animatable(0f) }

        LaunchedEffect(key1 = true) {
            offsetY.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
            )

            delay(500L)
            textOpacity.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
            )

            delay(2000L)
            visible = false
        }

        if (visible) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF2F2F2)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Logo
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .offset(y = offsetY.value.dp)
                        .size(550.dp)
                        .padding(top = 150.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.padding(top = 130.dp)
                ) {
                    Text(
                        text = "From",
                        color = Color.Black,
                        style = TextStyle(fontSize = 20.sp),
                        modifier = Modifier.graphicsLayer(alpha = textOpacity.value)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "GoDrag",
                        style = TextStyle(fontSize = 24.sp, color = Color(0xFFAF6800)),
                        modifier = Modifier.graphicsLayer(alpha = textOpacity.value)
                    )
                }
            }
        } else {
            Navigator(screen = LoginScreen())
        }
    }

}