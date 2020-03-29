package com.dropbox.facts.domain.repository

import com.dropbox.facts.domain.carrier.DResult
import com.dropbox.facts.domain.usecase.facts.Facts
import io.reactivex.Observable

/**
 * The interface which defined the interactions which REMOTE world.
 *
 */
interface RemoteRepository {

    /**
     * fetch facts
     *
     * @return Observable<DResult<Facts>>
     */
    fun facts() : Observable<DResult<Facts>>
}