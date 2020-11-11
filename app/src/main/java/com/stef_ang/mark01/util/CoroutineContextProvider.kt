package com.stef_ang.mark01.util

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

// extendable Dispatchers so that VM testable with coroutine.
open class CoroutineContextProvider @Inject constructor() {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
    open val io: CoroutineContext by lazy { Dispatchers.IO }
}