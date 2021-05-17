package com.aexyn.basicutilslibrary

import com.aexyn.generalutils.base.BaseViewModel
import com.aexyn.generalutils.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider
) : BaseViewModel() {


}