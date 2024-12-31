

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.protolink.presentation.home.DetailViewModelImp
import com.example.protolink.presentation.home.FakeDetailViewModel

@Composable
fun DetailScreen(
    bookId: Int,
    navigateBack: () -> Unit,
    viewModel: DetailViewModelImp = hiltViewModel(),
) {
    Scaffold(
        topBar = { DetailScreenTopBar(bookId = bookId, navigateBack = navigateBack) },
    ) { insetPadding ->
        DetailScreenContent(
            modifier = Modifier.padding(paddingValues = insetPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
@Preview
private fun DetailScreenTopBar(
    bookId: Int,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(text = "DetailScreenTopBar: $bookId")
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description",
                )
            }
        },
    )
}

@Composable
@Preview
private fun DetailScreenContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "DetailScreenContent")
    }
}

@Preview
@Composable
fun DetailScreenPreview(
    modifier: Modifier = Modifier,
    viewModel: FakeDetailViewModel = FakeDetailViewModel(),
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        DetailScreenContent(modifier = Modifier.padding(paddingValues))
    }
}
