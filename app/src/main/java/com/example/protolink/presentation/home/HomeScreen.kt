@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.protolink.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { HomeScreenTopBar() },
    ) { paddingValues ->
        HomeScreenContent(modifier = Modifier.padding(paddingValues))
    }
}

@ExperimentalMaterial3Api
@Composable
@Preview
private fun HomeScreenTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "HomeScreenTopBar",
                style = MaterialTheme.typography.titleLarge,
            )
        },
    )
}

@Composable
@Preview
private fun HomeScreenContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "HomeScreenContent")
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { HomeScreenTopBar() },
    ) { paddingValues ->
        HomeScreenContent(modifier = Modifier.padding(paddingValues))
    }
}
