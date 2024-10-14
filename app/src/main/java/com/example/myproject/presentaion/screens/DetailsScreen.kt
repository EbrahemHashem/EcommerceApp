package com.example.myproject.presentaion.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import coil.compose.AsyncImage
import com.example.myproject.R
import com.example.myproject.data.model.Product
import com.example.myproject.presentaion.viewmodel.MainViewModel
import java.lang.reflect.Modifier

class DetailsScreen(val product: Product) : Screen {
    @Composable
    override fun Content() {
        Details(product)

    }
}

@Composable
fun Details(product: Product) {
    Column(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(model = "${product.image}", contentDescription = null)
        Row {
            Text(text = "${product.name}")
            Text(text = "${product.price}")

        }
        Text(text = "${product.description}")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Add to cart")

        }
    }

}