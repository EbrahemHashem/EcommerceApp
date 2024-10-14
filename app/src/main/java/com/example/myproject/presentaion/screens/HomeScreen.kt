package com.example.myproject.presentaion.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import com.example.myproject.presentaion.viewmodel.MainViewModel
import com.example.myproject.R
import com.example.myproject.util.CustomBottomNavigationBar
import com.example.myproject.util.FavouriteButton

class HomeScreen(val token: String) : Screen {
    @Composable
    override fun Content() {
        val viewModel = remember { MainViewModel() }
        val homeData = viewModel.homeResponse.collectAsState()
        val isLoading = viewModel.isLoading.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        val search = remember { mutableStateOf("") }



        LaunchedEffect(Unit) {
            viewModel.getHomeData(token = token, lang = "en")
            viewModel.getCategory(token = token, lang = "en")
        }

        if (isLoading.value) {
            AnimatedShimmer()
        } else {

            Scaffold(
                bottomBar = {
                    CustomBottomNavigationBar()
                },
                content = {

                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFF3F3F3))
                            .padding(it)
                    ) {

                        Spacer(modifier = Modifier.height(15.dp))

                        OutlinedTextField(
                            value = search.value,
                            onValueChange = { search.value = it },
                            textStyle = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black
                            ),
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                            ,
                            placeholder = {
                                Text(
                                    text = "Search",
                                    fontSize = 18.sp,
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.search),
                                    contentDescription = null,
                                    modifier = Modifier.size(28.dp),
                                    tint = Color.Gray
                                )
                            },
                            maxLines = 1,
                            singleLine = true,
                            shape = RoundedCornerShape(15.dp),
                        )

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(8.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(homeData.value?.data?.products ?: emptyList()) { product ->
                                Card(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    elevation = CardDefaults.cardElevation(8.dp),
                                    shape = RoundedCornerShape(12.dp) // Rounded corners for a softer look
                                ) {
                                    var isFavourite by remember { mutableStateOf(product.in_favorites) }
                                    Box(
                                        modifier = Modifier.fillMaxSize() // Use Box to allow for overlay of elements
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center,
                                            modifier = Modifier.background(Color(0xFFF3F3F3))
                                        ) {
                                            AsyncImage(
                                                model = product.image,
                                                contentDescription = null,
                                                contentScale = ContentScale.FillBounds,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(120.dp)
                                                    .clip(RoundedCornerShape(12.dp))
                                                    .clickable {
//                                                navigator.push(DetailsScreen(product))
                                                    }
                                            )

                                            Spacer(modifier = Modifier.height(8.dp))

                                            Text(
                                                text = product.name,
                                                modifier = Modifier.padding(
                                                    start = 8.dp, end = 8.dp
                                                ),
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = Color.DarkGray,
                                                maxLines = 1,
                                            )
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.SpaceAround,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                            ) {
                                                Text(
                                                    text = "$${product.price}",
                                                    fontSize = 18.sp,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = Color.Black,
                                                )

                                                Spacer(modifier = Modifier.width(5.dp))

                                                IconButton(onClick = { /*TODO*/ }) {
                                                    Icon(imageVector = Icons.Default.Add,
                                                        contentDescription =null )
                                                }
                                            }

                                        }

                                        Box(
                                            modifier = Modifier
                                                .align(Alignment.TopEnd)
                                                .padding(8.dp)
                                                .clickable {
                                                    isFavourite = !isFavourite
                                                }
                                        ) {
                                            FavouriteButton(isFavourite = isFavourite)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            )
        }//else
    }
}