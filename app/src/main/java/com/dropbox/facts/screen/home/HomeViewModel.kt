package com.dropbox.facts.screen.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dropbox.facts.R
import com.dropbox.facts.base.SingleLiveEvent
import com.dropbox.facts.domain.carrier.DResult
import com.dropbox.facts.domain.usecase.facts.Facts
import com.dropbox.facts.domain.usecase.facts.FetchFactsUseCase
import com.dropbox.facts.mapper.UIModelMapperFactory
import com.dropbox.facts.resources.StringResources
import com.dropbox.facts.screen.home.model.UIFactItem
import com.dropbox.facts.screen.home.model.UIFacts
import com.dropbox.facts.uicomponent.model.UIError
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * [ViewModel] which manages all the view related data that is to be fed into [HomeActivity]
 *
 * There is no reference to any View and/or Activity instance in this class.
 * Data is provided to the "UI"/view via LiveData objects.
 *
 * Each LiveData object controls the smallest meaningful view component within the entire
 * Activity layout, as required.
 *
 */
class HomeViewModel @Inject constructor(private val factsUseCase: FetchFactsUseCase,
                                        private val uiModelMapperFactory: UIModelMapperFactory,
                                        stringResources: StringResources) : ViewModel() {

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }

    override fun onCleared() {
        factsUseCase.dispose()
        super.onCleared()
    }

    val errorToastLiveData = SingleLiveEvent<UIError?>()

    val factsLiveData = MutableLiveData<List<UIFactItem>>()
    val actionBarTitleLiveData = MutableLiveData<String?>()

    private val loadingHeading = stringResources.getString(R.string.loading)
    private val errorHeading = stringResources.getString(R.string.error)

    fun init () {
        fetchFacts()
    }

    fun onSwipeRefreshRequested() {
        fetchFacts()
    }

    private fun fetchFacts() {

        actionBarTitleLiveData.postValue(loadingHeading)
        factsUseCase.execute(object: DisposableObserver<DResult<Facts>>(){

            override fun onComplete() {
                //Do nothing
            }

            override fun onNext(t: DResult<Facts>) {
                Log.d(TAG, "onNext: facts results response received")
                val model = uiModelMapperFactory.create<UIFacts, DResult<Facts>>(UIFacts::class.java).fromDomain(t)

                val data = model.data
                if(data != null) {
                    factsLiveData.postValue(data.facts)
                    actionBarTitleLiveData.postValue(data.title)
                } else {
                    errorToastLiveData.postValue(model.error)
                }
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError: facts results error received")
                errorToastLiveData.postValue(UIError(e.message))
                actionBarTitleLiveData.postValue(errorHeading)
            }

        }, null)
    }

}