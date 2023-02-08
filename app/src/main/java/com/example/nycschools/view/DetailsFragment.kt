package com.example.nycschools.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nycschools.R
import com.example.nycschools.databinding.SchoolSatBinding
import com.example.nycschools.model.SchoolSatItem
import com.example.nycschools.utils.UIState
import com.example.starwarsmvvm.utils.BaseFragment

private const val TAG = "DetailsFragment"
class DetailsFragment: BaseFragment() {
    private val binding by lazy {
        SchoolSatBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        schoolViewModel.SAT.observe(viewLifecycleOwner) {
                state -> when(state) {
            is UIState.LOADING -> {}
            is UIState.SUCCESS<*> -> {
                val sats = state.response as List<SchoolSatItem>
                binding.writing.text = getString(R.string.sat_writing, sats[0].satWritingAvgScore)
                binding.reading.text = "Reading Score: " + sats[0].satCriticalReadingAvgScore
                binding.math.text = "Math Score: " +sats[0].satMathAvgScore
                binding.schoolName.text = sats[0].schoolName
                binding.numberOfTestTaker.text = "Number of Test Taker: " + sats[0].numOfSatTestTakers
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
