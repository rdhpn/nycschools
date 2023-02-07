package com.example.nycschools.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SchoolsItem(
    @Json(name = "city")
    val city: String? = null,
    @Json(name = "code1")
    val code1: String? = null,
    @Json(name = "dbn")
    val dbn: String? = null,
    @Json(name = "directions1")
    val directions1: String? = null,
    @Json(name = "eligibility1")
    val eligibility1: String? = null,
    @Json(name = "phone_number")
    val phoneNumber: String? = null,
    @Json(name = "primary_address_line_1")
    val primaryAddressLine1: String? = null,
    @Json(name = "program1")
    val program1: String? = null,
    @Json(name = "school_email")
    val schoolEmail: String? = null,
    @Json(name = "school_name")
    val schoolName: String? = null,
    @Json(name = "school_sports")
    val schoolSports: String? = null,
    @Json(name = "seats101")
    val seats101: String? = null,
    @Json(name = "seats9ge1")
    val seats9ge1: String? = null,
    @Json(name = "seats9swd1")
    val seats9swd1: String? = null,
    @Json(name = "state_code")
    val stateCode: String? = null,
    @Json(name = "subway")
    val subway: String? = null,
    @Json(name = "total_students")
    val totalStudents: String? = null,
    @Json(name = "transfer")
    val transfer: String? = null,
    @Json(name = "website")
    val website: String? = null,
    @Json(name = "zip")
    val zip: String? = null
)