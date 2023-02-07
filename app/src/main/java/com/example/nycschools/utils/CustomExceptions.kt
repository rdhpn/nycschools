package com.example.nycschools.utils

class NullSchoolResponse(message: String = "School response is null") : Exception(message)
class FailureResponse(message: String?) : Exception(message)