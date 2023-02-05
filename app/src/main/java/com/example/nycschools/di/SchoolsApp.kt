package com.example.nycschools.di

import android.app.Application
import android.util.Log

class SchoolsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("SchoolsApp", "onCreate: $this")
        schoolsComponent = DaggerSchoolsComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object {
        lateinit var schoolsComponent: SchoolsComponent
    }
}