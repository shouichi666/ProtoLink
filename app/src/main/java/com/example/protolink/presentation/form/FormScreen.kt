import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.protolink.presentation.form.FakeFormViewModel
import com.example.protolink.presentation.form.FormViewModelImp

@Composable
fun FormScreen(
    navController: NavController? = null,
    viewModel: FormViewModelImp = hiltViewModel(),
) {
    val scaffoldState = remember { SnackbarHostState() }
    val snackbarMessage by viewModel.snackbarMessage.collectAsState()

    LaunchedEffect(snackbarMessage) {
        snackbarMessage.message?.let { message ->
            val results =
                scaffoldState.showSnackbar(
                    message = message,
                    withDismissAction = true,
                )

            when (results) {
                SnackbarResult.ActionPerformed,
                SnackbarResult.Dismissed,
                -> viewModel.dismissSnackbar()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = scaffoldState) },
        topBar = { FormScreenTopBar(navController = navController) },
    ) { insetPadding ->
        FormScreenContent(
            modifier = Modifier.padding(paddingValues = insetPadding),
            viewModel = viewModel,
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun FormScreenTopBar(navController: NavController?) {
    TopAppBar(
        title = {
            Text("Form")
        },
        navigationIcon = {
            IconButton(onClick = { navController?.popBackStack() }) {
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
private fun FormScreenContent(
    modifier: Modifier = Modifier,
    viewModel: FormViewModelImp?,
) {
    val state = viewModel?.state?.value

    Box(
        modifier =
            modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .imePadding(),
    ) {
        LazyColumn {
            item {
                OutlinedTextField(
                    value = state?.userNamed ?: "",
                    singleLine = true,
                    onValueChange = { viewModel?.onChangeUserName(userNamed = it) },
                    label = { Text(text = "ユーザー名") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next,
                        ),
                )

                Spacer(modifier = Modifier.padding(4.dp))

                OutlinedTextField(
                    value = state?.password ?: "",
                    singleLine = true,
                    onValueChange = { viewModel?.onChangePassword(password = it) },
                    label = { Text(text = "パスワード") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation =
                        when {
                            state?.isPasswordVisible == false -> PasswordVisualTransformation()
                            else -> VisualTransformation.None
                        },
                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done,
                        ),
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(alignment = Alignment.Center),
                ) {
                    Checkbox(
                        modifier = Modifier,
                        checked = state?.isPasswordVisible ?: false,
                        onCheckedChange = { viewModel?.onChangeIsPasswordVisible(isPasswordVisible = it) },
                    )
                    Text(text = "パスワードを表示する")
                }
            }
        }

        // 送信ボタン
        Button(
            onClick = { viewModel?.onTapAdd() },
            shape = RoundedCornerShape(10),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
        ) {
            Text("追加")
        }
    }
}

@Preview
@Composable
fun FormScreenPreview(
    modifier: Modifier = Modifier,
    viewModel: FakeFormViewModel = FakeFormViewModel(),
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        FormScreenContent(
            modifier = Modifier.padding(paddingValues),
            viewModel = null,
        )
    }
}
