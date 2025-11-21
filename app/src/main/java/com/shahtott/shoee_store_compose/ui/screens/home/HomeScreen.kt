package com.shahtott.shoee_store_compose.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.homeState
    HomeContent()
}

@Composable
fun HomeContent() {

}

@Preview(showSystemUi = true)
@Composable
fun HomeContentPreview() {
    HomeScreen()
}
