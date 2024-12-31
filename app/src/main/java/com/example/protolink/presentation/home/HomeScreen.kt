package com.example.protolink.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.protolink.presentation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController? = null,
    viewModel: HomeViewModelImp = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Home")
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController?.navigate(Route.HomeGroup.Form.route)
                },
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        },
    ) { insetPadding ->
        HomeScreenContent(
            modifier = Modifier.padding(top = insetPadding.calculateTopPadding()),
            navController = navController,
        )
    }
}

@Composable
@Preview
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
) {
    val listItems = List(100) { "Item #$it" }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp), // アイテム間の間隔
            contentPadding = PaddingValues(16.dp), // 全体のパディング
        ) {
            items(listItems.size) { index ->
                ListItem(
                    item = listItems[index],
                    onClicked = {
                        navController?.navigate(Route.HomeGroup.Detail.destination(id = index))
                    },
                )
            }
        }
    }
}

@Composable
fun ListItem(
    item: String,
    onClicked: () -> Unit,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(4.dp),
        onClick = onClicked,
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium,
            text = item,
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview(
    modifier: Modifier = Modifier,
    viewModel: FakeLoginViewModel = FakeLoginViewModel(),
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        HomeScreenContent(modifier = Modifier.padding(paddingValues))
    }
}
