package com.indexer.bilemo.base

sealed class Resource<out T : Any>{
    object Loading : Resource<Nothing>()
    data class Success<T : Any>(val value: T) : Resource<T>()
    data class Fail(val error: GenericError) : Resource<GenericError>()

    val isLoading get() = this is Loading
    val isFail get() = (this as? Fail)?.error
    val valueOrNull get() = (this as? Success)?.value
}