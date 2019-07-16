package com.example.leonanta.finalproject.utils

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class ContextProviderTest : CoroutineContextProvider() {
    override val main: CoroutineContext = Unconfined
}