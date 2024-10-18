package com.example.myproject.presentaion.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myproject.presentaion.viewmodel.MainViewModel

@Composable

fun CartContent(token: String, viewModel: MainViewModel){
    val screenWidth = LocalConfiguration.current.screenWidthDp
    viewModel.getCartData(token = token, lang = "en")
    val cart = viewModel.cartResponse.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Basket",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(cart.value?.data?.cartItems?:emptyList()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .width(screenWidth.dp / 2)
                                .fillMaxHeight(),
                            model = it.product?.image,
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                        )
                        val words = it.product?.name?.split(" ")
                        val firstThreeWords = words?.take(2)?.joinToString(" ")
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "$firstThreeWords", fontWeight = FontWeight.Bold,fontSize = 18.sp)
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(text = "${it.product?.price} \$",fontWeight = FontWeight.Bold,fontSize = 14.sp)

                        }

                    }

                }

            }
        }


    }

}