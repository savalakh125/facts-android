package com.dropbox.facts.thread

import com.dropbox.facts.domain.thread.ObserverThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Implementation of [ObserverThread] interface to
 * provide the observer thread in case of thread
 * operations.
 */
class UIThread @Inject constructor(): ObserverThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()

}
