package com.example.nycschools.utils

import com.example.nycschools.model.SchoolsItem
import com.example.nycschools.model.SchoolsResponseItem

sealed class UIState<out T> {
    object LOADING : UIState<Nothing>()
    data class SUCCESS<T>(val response: T) : UIState<T>()
    data class ERROR(val error: Exception) : UIState<Nothing>()
}
