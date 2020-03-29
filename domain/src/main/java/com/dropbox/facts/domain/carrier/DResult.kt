package com.dropbox.facts.domain.carrier

/**
 * The class that contains/carries the data that is transmitted through
 * the reactive streams.
 *
 * onError() in a reactive stream signifies that an unrecoverable error has occurred.
 * But there are many instances where the error is not unrecoverable and hence
 * all such recoverable errors are to be provided through onNext() via this carrier class.
 *
 */
data class DResult<T> (var success: T? = null,
                       var error: DError? = null)