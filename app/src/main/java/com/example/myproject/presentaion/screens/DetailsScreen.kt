package com.example.myproject.presentaion.screens

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import coil.size.Size
import com.example.myproject.data.model.LoginRequest
import com.example.myproject.data.model.Product
import org.w3c.dom.Text
import java.util.Spliterator
import java.util.function.Predicate.not


class DetailsScreen(val product: Product) : Screen {
    @Composable
    override fun Content() {
        Details(product)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Details(product: Product) {
    val navigator = LocalNavigator.currentOrThrow

    Scaffold(
        Modifier.padding(top = 30.dp),
//        top bar
        topBar = {
            Row(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
//            back icon
                IconButton(
                    onClick = { navigator.pop() },
                ) {
                    Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null)
                }
                // fav icon
                IconButton(
                    onClick = { /*navigate to fav screen*/ },
                ) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = "Localized description")
                }
            }
        }) {
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(PaddingValues(16.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//            image
            AsyncImage(
                model = "${product.image}",
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
//            row of name and price
            Row(
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val words = product.name.split(" ")
                val firstThreeWords = words.take(3).joinToString(" ")
                Text(text = firstThreeWords, fontSize = 24.sp)
                Text(text = "${product.price}", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            }
            Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

//            row of description

            var isTapped = remember { mutableStateOf(false) }
            Row(
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Description", fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                Text(text = "${product.description}")
                IconButton(
                    onClick = { isTapped.value = !isTapped.value },
                ) {


                    Icon(
                        if (isTapped.value) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )
                }
            }
            if (isTapped.value) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "${product.description}", modifier = Modifier.verticalScroll(
                            rememberScrollState()
                        )
                    )

                }
            }
            Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
//            button add to Cart
            Button(
                modifier = Modifier
                    .width(225.dp)
                    .height(65.dp),
                shape = RoundedCornerShape(12.dp),

                onClick = {
//                    navigate to cart screen
                },
                colors = ButtonDefaults.buttonColors(Color.Black)


            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.scale(1.5f)
                )
                Spacer(modifier = androidx.compose.ui.Modifier.width(16.dp))
                Text(
                    text = "Add to Cart",
                    fontSize = 24.sp,
                    color = Color.White,
                )
            }

        }

    }


}