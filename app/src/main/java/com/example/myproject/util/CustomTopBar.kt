package com.example.myproject.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myproject.R

@Composable
fun CustomTopBar(tintColor: Color, onBackButtonPressed: () -> Unit = {},
                 onFavButtonPressed: () -> Unit = {}) {

    val interactionSource = remember { MutableInteractionSource() }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .offset(y = 10.dp)
    ) {

        Image(
            painterResource(id = R.drawable.back),
            colorFilter = ColorFilter.tint(tintColor),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxHeight()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onBackButtonPressed.invoke()
                }
        )



//            Image(
//                painterResource(id = R.drawable.fav),
//                contentDescription = null,
//                contentScale = ContentScale.FillHeight,
//                colorFilter = ColorFilter.tint(tintColor),
//                modifier = Modifier
//                    .padding(5.dp)
//                    .fillMaxHeight()
//                    .clickable(
//                        interactionSource = interactionSource,
//                        indication = null
//                    ) {
//                        onFavButtonPressed.invoke()
//                    }
//            )
//            Spacer(modifier = Modifier.width(5.dp))




    }

}
