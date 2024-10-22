package com.example.myproject.util

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String, leading: ImageVector
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, fontSize = 16.sp) },
        leadingIcon = { Icon(imageVector = leading, contentDescription = null) },
        textStyle = LocalTextStyle.current.copy(fontSize = 18.sp, color = Color.Black)
    )
}
