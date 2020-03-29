package com.dropbox.facts.domain.thread

import io.reactivex.Scheduler

/**
 * The interface which defines the observer thread implementations
 */
interface ObserverThread {

    /**
     * The thread which shall be used to observe the results
     * of all the threaded operations in use-cases
     */
    val scheduler: Scheduler

}