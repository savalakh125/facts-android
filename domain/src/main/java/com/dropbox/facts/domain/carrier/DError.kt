package com.dropbox.facts.domain.carrier

/**
 * The base class to contain any errors encountered
 * during the functioning of the use-cases.
 */
data class DError (var message: String = "")