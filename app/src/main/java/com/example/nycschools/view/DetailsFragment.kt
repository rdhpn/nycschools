package com.example.nycschools.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.DialogFragmentNavigatorDestinationBuilder
import com.example.nycschools.databinding.SchoolSatBinding
import com.example.nycschools.model.SchoolsResponseItem
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
                schoolViewModel.getSAT()
                val sats = state.response as SchoolsResponseItem
                binding.writing.text = sats.satWritingAvgScore
                binding.reading.text = sats.satCriticalReadingAvgScore
                binding.math.text = sats.satMathAvgScore
                binding.schoolName.text = sats.schoolName
                binding.numberOfTestTaker.text = sats.numOfSatTestTakers
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
