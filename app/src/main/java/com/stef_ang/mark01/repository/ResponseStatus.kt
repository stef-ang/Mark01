package com.stef_ang.mark01.repository

sealed class ResponseStatus {
    object Success: ResponseStatus()
    data class Error(val error: String): ResponseStatus()
}