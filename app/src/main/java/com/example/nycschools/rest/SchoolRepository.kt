package com.example.nycschools.rest

import com.example.nycschools.model.SchoolsItem
import com.example.nycschools.model.SchoolsResponseItem
import com.example.nycschools.utils.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface SchoolRepository {
    fun getSAT(id: String): Flow<UIState<SchoolsResponseItem>>
    fun getSchools(): Flow<UIState<List<SchoolsItem>>>
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
                    val states = mutableListOf<SchoolsItem>()
                    states.addAll(it)
                    emit(UIState.SUCCESS(states))
                } ?: throw NullPeopleResponse()
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

    override fun getSAT(id: String): Flow<UIState<SchoolsResponseItem>> = flow {
        emit(UIState.LOADING)
        try {
            val response = api.getSAT(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    val states = mutableListOf<SchoolsResponseItem>()
                    states.add(it)
                    emit(UIState.SUCCESS(states[0]))
                } ?: throw NullPeopleResponse()
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }
}
