package com.example.myproject.presentaion.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import com.example.myproject.presentaion.viewmodel.MainViewModel
import com.example.myproject.util.CustomBottomNavigationBar


@Composable
fun FavouriteContent(token: String, viewModel: MainViewModel) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    viewModel.getFavourites(token = token, lang = "en")
    val favourites = viewModel.favouriteResponse.collectAsState()
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Favourites",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(favourites.value?.data?.data?: emptyList()) {
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
                            Text(text = "${it.product?.price} \$",fontWeight = FontWeight.Bold,fontSize = 14.sp)
                            Button(onClick = { /*TODO*/ }) {
                                Text(text = "Add to cart")
                            }

                        }

                    }

                }

            }
        }

    }
}
