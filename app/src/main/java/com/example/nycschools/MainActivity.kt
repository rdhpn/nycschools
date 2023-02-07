package com.example.nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.nycschools.databinding.ActivityMainBinding
import com.example.nycschools.di.SchoolsApp

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        SchoolsApp.schoolsComponent.inject(this)

        val navHost = supportFragmentManager.findFragmentById(R.id.frag_container) as NavHostFragment
        setupActionBarWithNavController(navHost.navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}