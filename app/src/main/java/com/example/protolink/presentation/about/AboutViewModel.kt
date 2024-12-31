package com.example.protolink.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.protolink.data.TemplateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class AboutViewModel : ViewModel() {
    sealed class State {
        data class Login(
            val token: String,
        ) : State()
    }

    abstract val state: MutableStateFlow<State>
}

@HiltViewModel
class AboutViewModelImp @Inject constructor(
    private val repository: TemplateRepository,
) : AboutViewModel() {
    override val state: MutableStateFlow<State>
        get() = _state

    private val _state = MutableStateFlow<State>(State.Login(""))

    init {
        println("AboutViewModel init()")
        viewModelScope.launch { repository.getT() }
    }
}

class FakeAboutViewModel : AboutViewModel() {
    override val state: MutableStateFlow<State>
        get() =
            MutableStateFlow(
                State.Login("Fake Success! UseCase will return login token."),
            )
}
