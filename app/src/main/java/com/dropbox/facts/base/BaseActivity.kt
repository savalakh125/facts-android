package com.dropbox.facts.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dropbox.facts.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * A generic base Activity class which defines and handles the following for each child Activity instance:
 *
 * - The view bindings of type [T]
 * - The view model [VIEW_MODEL] which is to be associated with the respective Activity.
 * - Handles Dagger injections into the actual instance of each [Activity], which inherits from this class.
 * - Toasts that would like to be shown.
 *
 * NOTE:
 * There is only one activity in this app and thus all this could have been written in there.
 * But that would mean that the code is not scalable and future development ready.
 * Throughout the app, there are scenarios which have been architected to make them future development ready.
 * This is one of them.
 */
abstract class BaseActivity<T: ViewDataBinding, VIEW_MODEL : ViewModel> : DaggerAppCompatActivity() {

    protected lateinit var binding: T

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: VIEW_MODEL

    protected val TAG = this.javaClass.simpleName

    /**
     * The layout resource of the activity
     */
    @get:LayoutRes
    protected abstract val layoutResource: Int

    /**
     * The class type of the [ViewModel] that is to be associated with the inheriting activity
     * This is needed to pass the class type in order to get the
     * respective [ViewModel] from [ViewModelProviders]
     */
    protected abstract val viewModelClassType: Class<VIEW_MODEL>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutResource)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClassType)
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }


    /**
     * Show a toast.
     * In case a null string is passed, the default error message is shown:
     * [R.string.unknown_error]
     */
    protected fun showToast(message: String?) {
        Toast.makeText(this, message?:getString(R.string.unknown_error), Toast.LENGTH_LONG).show()
    }

}