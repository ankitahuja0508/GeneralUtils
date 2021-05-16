package com.aexyn.generalutils.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val navigationEventChannel = Channel<NavDirections>()

    val navigationEventFlow = navigationEventChannel.receiveAsFlow()

    fun navigate(navDirections: NavDirections) = viewModelScope.launch {
        navigationEventChannel.send(navDirections)
    }

}