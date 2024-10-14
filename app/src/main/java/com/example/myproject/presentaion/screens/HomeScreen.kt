package com.example.myproject.presentaion.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
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
import com.example.myproject.util.FavouriteButton

class HomeScreen(val token: String) :
    Screen {
    @Composable
    override fun Content() {
        val viewModel = remember { MainViewModel() }
        val homeData = viewModel.homeResponse.collectAsState()
        val isLoading = viewModel.isLoading.collectAsState()
        val categoryData = viewModel.categoryResponse.collectAsState()
        val navigator = LocalNavigator.currentOrThrow


        LaunchedEffect(Unit) {
            viewModel.getHomeData(
                token = token,
                lang = "en",
            )
            viewModel.getCategory(token = token, lang = "en")
        }

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF3F3F3))
        ) {
            if (isLoading.value) {
                AnimatedShimmer()
            } else {
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(110.dp)
                ) {
                    items(homeData.value?.data?.banners ?: emptyList()) {
                        Card(
                            modifier = Modifier
                                .padding(3.dp)
                                .height(85.dp)
                                .width(150.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            AsyncImage(
                                model = it.image, contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                            )
                        }
                    }
                }
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    item {
                        Box(
                            contentAlignment = Alignment.Center, modifier = Modifier.padding(5.dp)
                        ) {
                            Canvas(modifier = Modifier.size(80.dp), onDraw = {
                                drawCircle(color = Color.White)
                            })
                            Image(
                                painter = painterResource(id = R.drawable.all),
                                contentDescription = null,
                                modifier = Modifier.size(45.dp)

                            )
                        }
                    }
                    items(categoryData.value?.data?.data ?: emptyList()) {
                        Box(
                            contentAlignment = Alignment.Center, modifier = Modifier.padding(5.dp)
                        ) {
                            Canvas(modifier = Modifier.size(80.dp), onDraw = {
                                drawCircle(color = Color.White)
                            })
                            AsyncImage(
                                model = it.image,
                                contentDescription = null,
                                modifier = Modifier.size(60.dp)
                            )
                        }
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(homeData.value?.data?.products ?: emptyList()) {
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(8.dp),
                            shape = RoundedCornerShape(12.dp) // Rounded corners for a softer look
                        ) {
                            var isFavourite by remember { mutableStateOf(it.in_favorites) }
                            Box(
                                modifier = Modifier.fillMaxSize() // Use Box to allow for overlay of elements
                            ) {
                                Column(
                                    modifier = Modifier.padding(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    AsyncImage(
                                        model = it.image,
                                        contentDescription = null,
                                        contentScale = ContentScale.FillBounds,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(120.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .clickable {
//                                                navigate to details screen
                                                navigator.push(DetailsScreen(it))
                                            }
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = it.name,
                                        modifier = Modifier.padding(
                                            start = 8.dp, end = 8.dp, bottom = 4.dp
                                        ),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(
                                        text = "$${it.price}",
                                        modifier = Modifier.padding(horizontal = 8.dp),
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color(0xFF4CAF50)
                                    )
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
    }
}