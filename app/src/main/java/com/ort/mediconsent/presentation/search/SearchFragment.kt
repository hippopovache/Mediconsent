package com.ort.mediconsent.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ort.mediconsent.R

class SearchFragment : Fragment() {

    private lateinit var searchEditText: EditText

    private val viewModel: SearchViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchEditText = view.findViewById(R.id.search_layout_name)

        viewModel.state.observe(viewLifecycleOwner, ::updateState)

        /*arguments?.getString(KEY_ID)?.let {
            viewModel.getUserRdv(it)
        }*/
    }

    private fun updateState(state: SearchState) {
        when (state) {
            is SearchState.ErrorState -> {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            }
            is SearchState.LoadingState -> {
                Toast.makeText(requireContext(), "Loading", Toast.LENGTH_LONG).show()
            }
            is SearchState.SuccessState -> {
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                /*if (state.movie.poster.isNotEmpty() && state.movie.poster.isNotBlank()) {
                    Picasso.get().load(state.movie.poster).into(image)
                }*/
            }
        }
    }

}
