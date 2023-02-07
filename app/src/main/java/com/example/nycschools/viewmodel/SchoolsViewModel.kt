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

private const val TAG = "SchoolsViewModel"

//init {
//    getSAT()
//}

class SchoolsViewModel (
    private val schoolRepository: SchoolRepository,
    private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    init {
        getAllSchools()
        getSAT()
    }

    var dbn = ""
    var fragmentState: Boolean = false

    private val _SAT: MutableLiveData<UIState<SchoolsResponseItem>> = MutableLiveData(UIState.LOADING)
    val SAT: LiveData<UIState<SchoolsResponseItem>> get() = _SAT

    private val _schools: MutableLiveData<UIState<List<SchoolsItem>>> = MutableLiveData(UIState.LOADING)
    val schools: LiveData<UIState<List<SchoolsItem>>> get() = _schools

    fun getAllSchools() {
        viewModelScope.launch(ioDispatcher) {
            schoolRepository.getSchools().collect {
                _schools.postValue((it))
//                try {
//                    val response = serviceApi.getAllSchools()
//                    if (response.isSuccessful){
//                        response.body()?.let {
//                            // this post value works in main thread and worker thread
//                            _schools.postValue(it)
////                            withContext(Dispatchers.Main) {
//                                // this set value only works in the main thread
//                                // _schools.value = UIState.SUCCESS(it)
//                                Log.d(TAG, "onCreate: $it")
//                            }
//                        } ?: throw Exception("Error null schools response")
//                    } else {
//                        throw Exception(response.errorBody()?.string())
//                    }
//                } catch (e: Exception) {
//                    _schools.postValue((UIState.ERROR(e)))
//                    Log.e(TAG, "onCreate: ${e.localizedMessage}")
//                }
        }
    }
}

    fun getSAT(id: String? = null) {
        id?.let {
            viewModelScope.launch(ioDispatcher) {
                Log.d("SchoolsViewModel", "getSAT: $it")
                schoolRepository.getSAT(it).collect {
                    _SAT.postValue(it)
                }
            }
        }
    }
}