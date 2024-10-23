package com.example.myproject.presentaion.screens

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myproject.data.model.cart.AddOrDeleteCartRequest
import com.example.myproject.presentaion.viewmodel.MainViewModel

@Composable

fun CartContent(token: String, viewModel: MainViewModel){
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val context = LocalContext.current
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
            contentPadding = PaddingValues(16.dp)
        ) {
            items(cart.value?.data?.cartItems ?: emptyList()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(8.dp)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .width(150.dp)
                                .height(130.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.LightGray),
                            model = it.product?.image,
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        val words = it.product?.name?.split(" ")
                        val firstThreeWords = words?.take(2)?.joinToString(" ")

                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "$firstThreeWords",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color.DarkGray,
                                maxLines = 1,
                                modifier = Modifier.padding(top = 8.dp)
                            )

                            Text(
                                text = "${it.product?.price} \$",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color(0xFF008577)
                            )

                            Button(
                                onClick = {
                                    viewModel.getCartData(token = token, lang = "en")
                                    viewModel.getAddCart(
                                        token =token,
                                        addOrDeleteCartRequest = AddOrDeleteCartRequest (
                                            productId = it.product?.id
                                        ),
                                        onSuccess = {
                                            Toast
                                                .makeText(
                                                    context,
                                                    "$it",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()

                                        })

                                },
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            ) {
                                Text(text = "Remove from Cart")
                            }
                        }
                    }
                }
            }
        }


    }

}