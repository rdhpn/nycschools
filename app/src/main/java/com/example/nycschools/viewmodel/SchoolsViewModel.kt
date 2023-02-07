package com.example.nycschools.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.model.SchoolsItem
import com.example.nycschools.model.SchoolsResponseItem
import com.example.nycschools.rest.SchoolRepository
import com.example.nycschools.rest.ServiceApi
import com.example.nycschools.service.Network.serviceApi
import com.example.nycschools.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val TAG = "SchoolsViewModel"

//init {
//    getSAT()
//}

class SchoolsViewModel @Inject constructor(
    private val schoolRepository: SchoolRepository,
    private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    init {
        getAllSchools()
        getSAT()
    }

    var dbn = ""

    private val _SAT: MutableLiveData<UIState<SchoolsResponseItem>> = MutableLiveData(UIState.LOADING)
    val SAT: LiveData<UIState<SchoolsResponseItem>> get() = _SAT

    private val _schools: MutableLiveData<UIState<List<SchoolsItem>>> = MutableLiveData(UIState.LOADING)
    val schools: LiveData<UIState<List<SchoolsItem>>> get() = _schools

    fun getAllSchools() {
        viewModelScope.launch(ioDispatcher) {
            schoolRepository.getSchools().collect {
                _schools.postValue((it))
        }
    }
}

    fun getSAT(dbn: String? = null) {
        dbn?.let {
            viewModelScope.launch(ioDispatcher) {
                Log.d("SchoolsViewModel", "getSAT: $it")
                schoolRepository.getSAT(it).collect {
                    _SAT.postValue(it)
                }
            }
        }
    }
}