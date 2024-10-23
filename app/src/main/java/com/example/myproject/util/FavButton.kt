package com.example.myproject.util

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun FavouriteButton(isFavourite: Boolean = false) {
    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(35.dp), onDraw = {
            drawCircle(color = Color.White)
        })

        if (isFavourite) {
            Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = "Filled Heart Icon",
                tint = Color.Red
            )
        } else {
            Icon(
                imageVector = Icons.Rounded.FavoriteBorder,
                contentDescription = "Empty Heart Icon"
            )
        }
    }
}