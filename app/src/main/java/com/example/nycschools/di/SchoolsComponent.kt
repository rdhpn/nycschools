package com.example.nycschools.di

import com.example.nycschools.MainActivity
import com.example.nycschools.view.DetailsFragment
import com.example.nycschools.view.SchoolsFragment
import com.example.starwarsmvvm.utils.BaseFragment
import dagger.Component

@Component(modules = [
    NetworkModule::class,
    RepositoryModule::class,
    ApplicationModule::class
])
interface SchoolsComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(baseFragment: BaseFragment)
}