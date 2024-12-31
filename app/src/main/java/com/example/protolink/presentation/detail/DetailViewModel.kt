package com.example.protolink.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.protolink.data.TemplateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class DetailViewModel : ViewModel() {
    sealed class State {
        data class Login(
            val token: String,
        ) : State()
    }

    abstract val state: MutableStateFlow<State>
}

@HiltViewModel
class DetailViewModelImp @Inject constructor(
    private val repository: TemplateRepository,
) : DetailViewModel() {
    override val state: MutableStateFlow<State>
        get() = _state

    private val _state = MutableStateFlow<State>(State.Login(""))

    init {
        println("DetailViewModel init()")
        viewModelScope.launch { repository.getT() }
    }
}

class FakeDetailViewModel : DetailViewModel() {
    override val state: MutableStateFlow<State>
        get() =
            MutableStateFlow(
                State.Login("Fake Success! UseCase will return login token."),
            )
}
