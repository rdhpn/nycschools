package com.example.nycschools.adapter

import com.example.nycschools.model.SchoolsItem

sealed class ViewType {
    data class SCHOOL(val schoolItem: SchoolsItem) : ViewType()
    data class LETTER(val letter: String): ViewType()
}
