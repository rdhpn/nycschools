package com.example.nycschools.rest

import com.example.nycschools.model.SchoolsItem
import com.example.nycschools.model.SchoolSatItem
import com.example.nycschools.utils.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface SchoolRepository {
    fun getSchools(): Flow<UIState<List<SchoolsItem>>>
    fun getSAT(dbn: String): Flow<UIState<SchoolSatItem>>
}

class SchoolRepositoryImpl @Inject constructor(
    private val api: ServiceApi
) : SchoolRepository {

    override fun getSchools(): Flow<UIState<List<SchoolsItem>>> = flow {
        emit(UIState.LOADING)
        try {
            val response = api.getAllSchools()
            if (response.isSuccessful) {
                response.body()?.let {
//                    val states = mutableListOf<SchoolsItem>()
//                    states.addAll(it)
                    emit(UIState.SUCCESS(it))
                } ?: throw NullSchoolResponse()
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

    override fun getSAT(dbn: String): Flow<UIState<SchoolSatItem>> = flow {
        emit(UIState.LOADING)
        try {
            val response = api.getSAT(dbn)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it))
                } ?: throw NullSchoolResponse()
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }
}
