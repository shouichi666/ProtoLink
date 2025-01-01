package com.example.protolink.presentation.form

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.protolink.data.TemplateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class FormViewModel : ViewModel() {
    data class FormValues(
        val userNamed: String,
        val password: String,
        val isPasswordVisible: Boolean,
    )

    sealed class FormEvent {
        data class SnackBar(
            var message: String?,
        ) : FormEvent()
    }

    abstract val state: MutableState<FormValues>
    abstract val event: MutableStateFlow<FormEvent.SnackBar>
}

@HiltViewModel
class FormViewModelImp @Inject constructor(
    private val repository: TemplateRepository,
) : FormViewModel() {
    override val state: MutableState<FormValues>
        get() = _state

    override val event: MutableStateFlow<FormEvent.SnackBar>
        get() = _event

    private val _state =
        mutableStateOf(
            FormValues(
                userNamed = "",
                password = "",
                isPasswordVisible = false,
            ),
        )

    private val _event =
        MutableStateFlow(
            FormEvent.SnackBar(message = null),
        )

    // 現在のSnackbarメッセージを管理
    val snackbarMessage: StateFlow<FormEvent.SnackBar> = _event

    init {
        viewModelScope.launch { repository.getT() }
    }

    fun onChangeUserName(userNamed: String) {
        _state.value = _state.value.copy(userNamed = userNamed)
    }

    fun onChangePassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun onChangeIsPasswordVisible(isPasswordVisible: Boolean) {
        _state.value = _state.value.copy(isPasswordVisible = isPasswordVisible)
    }

    fun onTapAdd() {
        viewModelScope.launch {
            _event.emit(value = _event.value.copy(message = "どう？"))
        }
    }

    fun dismissSnackbar() {
        viewModelScope.launch {
            _event.emit(value = _event.value.copy(message = null))
        }
    }
}

class FakeFormViewModel : FormViewModel() {
    override val event: MutableStateFlow<FormEvent.SnackBar>
        get() = TODO("Not yet implemented")

    override val state: MutableState<FormValues>
        get() =
            mutableStateOf(
                FormValues(userNamed = "Fake Success! UseCase will return login token.", password = "", isPasswordVisible = false),
            )
}
