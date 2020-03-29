package com.dropbox.facts.uicomponent.model

/**
 * Based on the current set of requirements,
 * it is assumed that all the UI Models shall always
 * have ERROR of type [UIError]
 *
 * But in case there is a need to provide more complex errors,
 * the ERROR can also be made generic:
 * UIModel<DATA, ERROR: UIError>(var data: DATA? = null, var error: ERROR? = null)
 *
 */
open class UIModel<DATA>(var data: DATA? = null,
                    var error: UIError? = null)