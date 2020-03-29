package com.dropbox.facts.resources

import android.content.res.Resources

class DStringResources(private val resources: Resources) : StringResources {

    override fun getString(resId: Int, vararg formatArgs: String): String {
        return resources.getString(resId, *formatArgs)
    }

}