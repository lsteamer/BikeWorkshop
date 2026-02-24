package com.anjegonz.bikeworkshop.garage.domain.core

sealed interface Result<out D, out E: Error> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Failure<out E: Error>(val error: E): Result<Nothing, E>
}