package com.example.nycschools.di

import android.app.Application
import android.content.Context
import com.example.nycschools.rest.SchoolRepository
import com.example.nycschools.utils.SchoolsViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun providesContext(): Context =
        application.applicationContext

    @Provides
    fun provideViewModelFactory(
        repository: SchoolRepository,
        ioDispatcher: CoroutineDispatcher
    ): SchoolsViewModelFactory =
        SchoolsViewModelFactory(repository, ioDispatcher)
}