package com.stef_ang.mark01

import com.stef_ang.mark01.util.CoroutineContextProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class TestContextProvider : CoroutineContextProvider() {

    val testCoroutineDispatcher = TestCoroutineDispatcher()

    override val main: CoroutineContext = testCoroutineDispatcher
    override val io: CoroutineContext = testCoroutineDispatcher
}