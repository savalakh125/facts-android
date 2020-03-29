package com.dropbox.facts.screen.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dropbox.facts.UITestCase
import com.dropbox.facts.domain.usecase.facts.FetchFactsUseCase
import com.dropbox.facts.mapper.UIModelMapperFactory
import com.dropbox.facts.resources.StringResources
import com.dropbox.facts.screen.home.model.UIFactItem
import com.dropbox.facts.uicomponent.model.UIError
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HomeViewModelTest : UITestCase() {

    @Mock
    private lateinit var useCase: FetchFactsUseCase

    @Mock
    private lateinit var uiModelMapperFactory: UIModelMapperFactory

    @Mock
    private lateinit var stringResources: StringResources

    private lateinit var viewModel: HomeViewModel

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    private fun <T> uninitialized(): T = null as T

    @Mock
    private lateinit var titleObserver: Observer<String?>

    @Mock
    private lateinit var factsObserver: Observer<List<UIFactItem>?>

    @Mock
    private lateinit var errorObserver: Observer<UIError?>

    override fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = HomeViewModel(useCase, uiModelMapperFactory, stringResources)

        viewModel.actionBarTitleLiveData.observeForever(titleObserver)
        viewModel.factsLiveData.observeForever(factsObserver)
        viewModel.errorToastLiveData.observeForever(errorObserver)
    }

    @Test
    fun onSwipeRefreshRequestedTest() {
        viewModel.onSwipeRefreshRequested()

        Mockito.verify(titleObserver).onChanged(any())
        Mockito.verify(useCase).execute(any(), any())
    }

    @Test
    fun initTest() {
        viewModel.init()

        Mockito.verify(titleObserver).onChanged(any())
        Mockito.verify(useCase).execute(any(), any())

    }
}