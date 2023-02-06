package com.example.nycschools.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nycschools.R
import com.example.nycschools.adapter.SchoolAdapter
import com.example.nycschools.databinding.FragmentDetailsBinding
import com.example.nycschools.model.SchoolsResponseItem
import com.example.nycschools.utils.UIState
import com.example.starwarsmvvm.utils.BaseFragment

class DetailsFragment: BaseFragment() {
    private val binding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    private val schoolAdapter by lazy {
        SchoolAdapter {
            findNavController().navigate(R.id.action_DetailsFragment_to_SchoolsFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.schoolSat.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = schoolAdapter
        }

        schoolViewModel.SAT.observe(viewLifecycleOwner) {
                state -> when(state) {
            is UIState.LOADING -> {

            }
            is UIState.SUCCESS<*> -> {
                schoolViewModel.getSAT(state.response.toString())
            }
            is UIState.ERROR -> {
                AlertDialog.Builder(requireActivity())
                    .setTitle("Error occurred")
                    .setMessage(state.error.localizedMessage)
                    .setPositiveButton("RETRY") { dialog, _ ->
                        schoolViewModel.getSAT()
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

        schoolViewModel.getSAT()

        return binding.root
    }
}
