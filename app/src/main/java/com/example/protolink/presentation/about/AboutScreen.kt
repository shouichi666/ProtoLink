@file:OptIn(ExperimentalMaterial3Api::class)

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.protolink.presentation.home.AboutViewModelImp
import com.example.protolink.presentation.home.FakeAboutViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(viewModel: AboutViewModelImp = hiltViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("About")
                },
            )
        },
    ) { insetPadding ->
        AboutScreenContent(modifier = Modifier.padding(paddingValues = insetPadding))
    }
}

@Composable
@Preview
private fun AboutScreenContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "AboutScreenContent")
    }
}

@Preview
@Composable
fun AboutScreenPreview(
    modifier: Modifier = Modifier,
    viewModel: FakeAboutViewModel = FakeAboutViewModel(),
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        AboutScreenContent(modifier = Modifier.padding(paddingValues))
    }
}
