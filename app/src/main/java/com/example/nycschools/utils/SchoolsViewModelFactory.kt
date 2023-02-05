package com.example.nycschools.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nycschools.rest.SchoolRepository
import com.example.nycschools.viewmodel.SchoolsViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SchoolsViewModelFactory @Inject constructor(
    private val schoolRepository: SchoolRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SchoolsViewModel(schoolRepository, ioDispatcher) as T
    }
}