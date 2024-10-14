package com.example.myproject.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomNavigationBar() {
    val selectedItem = remember { mutableStateOf(0) }

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clip(RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
            .background(Color(0xFF212121)),
        containerColor = Color(0xFF212121),
        tonalElevation = 8.dp,

    ) {

        IconButton(
            onClick = { selectedItem.value = 0 },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                modifier = Modifier.size(35.dp),
                imageVector = Icons.Rounded.Home,
                contentDescription = "Home",
                tint = if (selectedItem.value == 0) Color(0xFFFBC02D) else Color.Gray
            )
        }
        IconButton(
            onClick = { selectedItem.value = 1 },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Rounded.Favorite,
                contentDescription = "Favorites",
                tint = if (selectedItem.value == 1) Color(0xFFFBC02D) else Color.Gray
            )
        }

        IconButton(
            onClick = { selectedItem.value = 2 },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Rounded.ShoppingCart,
                contentDescription = "Cart",
                tint = if (selectedItem.value == 2) Color(0xFFFBC02D) else Color.Gray
            )
        }
        IconButton(
            onClick = { selectedItem.value = 3 },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Rounded.Person,
                contentDescription = "Profile",
                tint = if (selectedItem.value == 3) Color(0xFFFBC02D) else Color.Gray
            )
        }
    }
}

