package com.example.nycschools.rest

import com.example.nycschools.model.SchoolsItem
import com.example.nycschools.model.SchoolsResponseItem
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {

    /**
     * method to get the schools from server
     */
    @GET(SCHOOL_PATH)
    suspend fun getAllSchools(): Response<List<SchoolsItem>>

//    @GET(SAT_PATH)
//    suspend fun getSAT(): Response<SchoolsResponseItem>

    @GET(ID_PATH)
    fun getSAT(
        @Query("id") id: String
    ): Response<SchoolsResponseItem>


    companion object {
        const val BASE_URL = "https://data.cityofnewyork.us/resource/"
        const val SCHOOL_PATH = "s3k6-pzi2.json"
        const val SAT_PATH = "f9bf-2cp4.json"
        private const val ID_PATH = "?dbn={id}"
    }
}