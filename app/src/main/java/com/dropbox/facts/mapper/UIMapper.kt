package com.dropbox.facts.mapper

/**
 * Interface that defines the mapping of the
 * [DOMAIN] models into respective [UI] models
 */
interface UIMapper<UI, DOMAIN> {

    /**
     * Convert [DOMAIN] into [UI]
     */
    fun fromDomain(domain: DOMAIN): UI

}