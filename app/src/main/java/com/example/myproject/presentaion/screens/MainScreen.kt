package com.example.myproject.presentaion.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.myproject.presentaion.viewmodel.MainViewModel
import com.example.myproject.util.CustomBottomNavigationBar


class MainScreen(val token: String) : Screen {
    @Composable
    override fun Content() {

        val viewModel = remember { MainViewModel() }

        val selectedItem by viewModel.selectedItem.collectAsState()

            val navigator = LocalNavigator.currentOrThrow

            Scaffold(
                bottomBar = {
                    CustomBottomNavigationBar(
                        selectedItem = selectedItem,
                        onItemSelected = { index -> viewModel.selectItem(index) }
                    )
                }
            ) { paddingValues -> // PaddingValues passed to content
                // Apply the padding values to avoid content being obscured by the BottomNavigationBar
                Box(modifier = Modifier.padding(paddingValues)) {
                    // Show content based on selected item
                    when (selectedItem) {
                        0 -> HomeContent(token, navigator)
//                        favourite screen
                        1 -> FavouriteContent(token, viewModel)
//                        cart screen
                        2 -> CartContent()
//                        profile screen
                        3 -> FavouriteContent(token, viewModel)
                    }
                }
            }

    }
}


