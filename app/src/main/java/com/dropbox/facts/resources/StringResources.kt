package com.dropbox.facts.resources

import androidx.annotation.StringRes

interface StringResources {

    fun getString(@StringRes resId: Int, vararg formatArgs: String): String

}