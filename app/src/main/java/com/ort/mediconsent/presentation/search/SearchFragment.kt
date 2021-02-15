package com.ort.mediconsent.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ort.mediconsent.R
import com.ort.mediconsent.presentation.MainActivity

class SearchFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var firstNameEditText: EditText
    private lateinit var searchButton: Button


    private val viewModel: SearchViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameEditText = view.findViewById(R.id.search_layout_name)
        firstNameEditText = view.findViewById(R.id.search_layout_firstname)
        searchButton = view.findViewById(R.id.search_button)

        searchButton.setOnClickListener {

            if (nameEditText.text.toString() != "" && firstNameEditText.text.toString() != "") {
                viewModel.getUserRdvForToday(
                    firstNameEditText.text.toString(), nameEditText.text.toString()
                )

            } else {
                Toast.makeText(
                    requireContext(),
                    getText(R.string.fillInfo),
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }

        viewModel.state.observe(viewLifecycleOwner, ::updateState)
    }

    private fun updateState(state: SearchState) = when (state) {
        is SearchState.ErrorState -> {
            Toast.makeText(requireContext(), getText(R.string.noExam), Toast.LENGTH_LONG)
                .show()
        }
        is SearchState.LoadingState -> {
        }
        is SearchState.SuccessState -> {
            val activity: MainActivity = activity as MainActivity
            activity.displayRdvListLayout(
                state.examen.id_examen,
                firstNameEditText.text.toString(),
                nameEditText.text.toString()
            )
        }
    }

}
