package com.akashev.citychargers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal abstract class BaseViewModel : ViewModel() {

    protected val mutableError = MutableSharedFlow<String?>()
    val error = mutableError.asSharedFlow()

    protected val mutableIsLoading = MutableStateFlow(false)
    val isLoading = mutableIsLoading.asStateFlow()

    protected open val onCoroutineError: (CoroutineContext, Throwable) -> Unit = { _, throwable ->
        viewModelScope.launch {
            mutableError.emit(throwable.localizedMessage)
        }
    }

    protected open val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler(onCoroutineError)
}