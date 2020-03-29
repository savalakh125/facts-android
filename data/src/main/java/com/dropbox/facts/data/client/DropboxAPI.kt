package com.dropbox.facts.data.client

import com.dropbox.facts.data.response.facts.APIFacts
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The Retrofit client for respective API calls.
 *
 * TODO: Consider using Single instead of Observable, where possible.
 */
interface DropboxAPI {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun facts(): Observable<APIFacts>

}