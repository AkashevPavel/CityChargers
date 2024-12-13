package com.akashev.citychargers.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

internal abstract class BaseViewModel : ViewModel() {
    protected abstract val onCoroutineError: (CoroutineContext, Throwable) -> Unit

    protected open val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler(onCoroutineError)
}