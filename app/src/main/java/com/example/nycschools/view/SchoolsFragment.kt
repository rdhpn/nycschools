package com.example.nycschools.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nycschools.R
import com.example.nycschools.adapter.SchoolAdapter
import com.example.nycschools.databinding.FragmentSchoolsBinding
import com.example.nycschools.model.SchoolsItem
import com.example.nycschools.utils.UIState
import com.example.starwarsmvvm.utils.BaseFragment

private const val TAG = "SchoolsFragment"
class SchoolsFragment : BaseFragment() {

    private val binding by lazy {
        FragmentSchoolsBinding.inflate(layoutInflater)
    }

    private val schoolAdapter by lazy {
        SchoolAdapter {
            schoolViewModel.getSAT(it.dbn)
            findNavController().navigate(R.id.action_SchoolsFragment_to_DetailsFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.schoolRv.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = schoolAdapter
        }

        schoolViewModel.schools.observe(viewLifecycleOwner) {
                state -> when(state) {
                is UIState.LOADING -> { }
                is UIState.SUCCESS<List<SchoolsItem>> -> {
                    schoolAdapter.updateSchools(state.response)
                }
                is UIState.ERROR -> {
                    AlertDialog.Builder(requireActivity())
                        .setTitle("Error occurred")
                        .setMessage(state.error.localizedMessage)
                        .setPositiveButton("RETRY") { dialog, _ ->
                            schoolViewModel.getAllSchools()
                            dialog.dismiss()
                        }
                        .setNegativeButton("DISMISS") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
            }
        }


        return binding.root
    }
}