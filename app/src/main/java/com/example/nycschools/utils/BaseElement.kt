package com.example.starwarsmvvm.utils

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nycschools.di.SchoolsApp
import com.example.nycschools.utils.SchoolsViewModelFactory
import com.example.nycschools.viewmodel.SchoolsViewModel
import javax.inject.Inject

open class BaseFragment : Fragment() {
    @Inject
    lateinit var schoolsViewModelFactory: SchoolsViewModelFactory

    protected val schoolViewModel: SchoolsViewModel by lazy {
        ViewModelProvider(requireActivity(), schoolsViewModelFactory)[SchoolsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SchoolsApp.schoolsComponent.inject(this)
    }

    protected fun showError(message: String, action: () -> Unit)  {
        AlertDialog.Builder(requireActivity())
            .setTitle("Error Occurred")
            .setMessage(message)
            .setPositiveButton("RETRY") { dialog, _ ->
                action()
                dialog.dismiss()
            }
            .setNegativeButton("DISMISS") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}
