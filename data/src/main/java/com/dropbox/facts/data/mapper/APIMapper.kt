package com.dropbox.facts.data.mapper

/**
 * Interface that defines the mapping of the
 * [DATA] models into respective [DOMAIN] models
 */
interface APIMapper<DATA, DOMAIN> {

    /**
     * Convert [DATA] into [DOMAIN]
     */
    fun toDomain(data: DATA): DOMAIN

}