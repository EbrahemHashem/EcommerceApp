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
fun CustomBottomNavigationBar(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    val selectedItemState = remember { mutableStateOf(selectedItem) }

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clip(RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
            .background(Color(0xFF212121)),
        containerColor = Color(0xFF212121),
        tonalElevation = 8.dp,

        ) {
//home icon
        IconButton(
            onClick = { selectedItemState.value = 0
                onItemSelected(0)
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                modifier = Modifier.size(35.dp),
                imageVector = Icons.Rounded.Home,
                contentDescription = "Home",
                tint = if (selectedItemState.value == 0) Color(0xFFFBC02D) else Color.Gray
            )
        }
//        favourite icon
        IconButton(
            onClick = {
                selectedItemState.value = 1
                onItemSelected(1)
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Rounded.Favorite,
                contentDescription = "Favorites",
                tint = if (selectedItemState.value == 1) Color(0xFFFBC02D) else Color.Gray
            )
        }
//        cart icon
        IconButton(
            onClick = { selectedItemState.value = 2
                onItemSelected(2)
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Rounded.ShoppingCart,
                contentDescription = "Cart",
                tint = if (selectedItemState.value == 2) Color(0xFFFBC02D) else Color.Gray
            )
        }
//        profile icon
        IconButton(
            onClick = { selectedItemState.value = 3
                onItemSelected(3)
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Rounded.Person,
                contentDescription = "Profile",
                tint = if (selectedItemState.value == 3) Color(0xFFFBC02D) else Color.Gray
            )
        }
    }
}

