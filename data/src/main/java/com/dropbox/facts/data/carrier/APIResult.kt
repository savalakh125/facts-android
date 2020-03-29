package com.dropbox.facts.data.carrier

/**
 * The class that contains/carries the data fetched by the DATA layer.
 *
 * onError() in a reactive stream signifies that an unrecoverable error has occurred.
 * But there are many instances where the error is not unrecoverable and hence
 * all such recoverable errors are to be provided through onNext() via this carrier class.
 *
 * PLEASE NOTE:
 * This is similar to models in DOMAIN layer. This is because models are independently
 * defined in each layer to maintain decoupling.
 *
 * Respective mappers [APIMapper] are responsible for converting the data between each layer.
 *
 */
open class APIResult<T> (val success: T? = null,
                         val error: APIError? = null)