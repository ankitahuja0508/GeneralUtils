package com.aexyn.basicutilslibrary

import androidx.lifecycle.viewModelScope
import com.aexyn.basicutilslibrary.data.HomeRepository
import com.aexyn.generalutils.api.Result
import com.aexyn.generalutils.base.BaseViewModel
import com.aexyn.generalutils.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(val repository: HomeRepository, val dispatchers: DispatcherProvider
) : BaseViewModel() {

    sealed class TestListEvent {
        class Success(val categoryList: ArrayList<String>, val message:String?): TestListEvent()
        class Failure(val errorText: String): TestListEvent()
        object AuthenticationFailed : TestListEvent()
        object NetworkError : TestListEvent()
        object Loading : TestListEvent()
        object Empty : TestListEvent()
    }

    private val _getList = MutableStateFlow<TestListEvent>(TestListEvent.Empty)
    val getList: StateFlow<TestListEvent> = _getList

    init {
        //testApi()
    }

    fun testApi(){
        var list: ArrayList<String>? = null
        list = repository.resultList
        list?.let {
            _getList.value = TestListEvent.Success(it, "")
        } ?: viewModelScope.launch(dispatchers.io) {
            _getList.value = TestListEvent.Loading
            when(val response = repository.getTestQuery()) {
                is Result.Error -> _getList.value = TestListEvent.Failure(response.message!!)
                is Result.AuthenticationFailed -> {
                    _getList.value = TestListEvent.AuthenticationFailed
                }
                is Result.NetworkError -> {
                    _getList.value = TestListEvent.NetworkError
                }
                is Result.Success -> {
                    _getList.value = TestListEvent.Success(repository.resultList!!, "")
                }
            }
        }
    }
}