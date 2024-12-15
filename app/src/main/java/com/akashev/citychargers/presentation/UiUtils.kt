package com.akashev.citychargers.presentation

import android.text.Editable
import android.text.TextWatcher
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.akashev.citychargers.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun Fragment.repeatOnStarted(
    state: Lifecycle.State = Lifecycle.State.STARTED,
    block: suspend CoroutineScope.() -> Unit,
) {
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(state, block)
    }
}

@OptIn(FlowPreview::class)
fun AutoCompleteTextView.onTextChanged(intervalMs: Long = 100) = callbackFlow {
    val listener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            runBlocking { send(s.toString()) }
        }
    }
    addTextChangedListener(listener)
    send(text.toString())
    awaitClose { removeTextChangedListener(listener) }
}.debounce(intervalMs).buffer(1, BufferOverflow.DROP_OLDEST)

fun AutoCompleteTextView.replaceText(value: CharSequence) {
    if (text?.toString() != value) {
        setText(value)
    }
}

fun Fragment.showErrorMessage(message: String?) {
    val errorMessage = message ?: getString(R.string.default_error_message)
    Snackbar.make(requireContext(), requireView(), errorMessage, Snackbar.LENGTH_LONG).show()
}