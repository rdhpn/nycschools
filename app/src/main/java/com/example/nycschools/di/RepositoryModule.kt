package com.example.nycschools.di

import com.example.nycschools.rest.SchoolRepository
import com.example.nycschools.rest.SchoolRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providesSchoolRepository(
        schoolRepositoryImpl: SchoolRepositoryImpl
    ): SchoolRepository
}