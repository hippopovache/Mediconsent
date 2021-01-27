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
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameEditText = view.findViewById(R.id.search_layout_name)
        firstNameEditText = view.findViewById(R.id.search_layout_firstname)
        searchButton = view.findViewById(R.id.search_button)

        searchButton.setOnClickListener {
            val activity: MainActivity? = activity as? MainActivity

            if (nameEditText.text.toString() != "" && firstNameEditText.text.toString() != "") {
                val examen = viewModel.getUserRdvForToday(
                        firstNameEditText.text.toString(), nameEditText.text.toString()
                )

                if (examen != null) {
                    if (examen.id_examen != null) {
                        Toast.makeText(requireContext(), "Exam trouvé", Toast.LENGTH_LONG)
                            .show()
                        //activity?.displaySearchLayout()
                    } else {
                        Toast.makeText(requireContext(), "Exam trouvé mais pas d'id", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Aucun exam trouvé", Toast.LENGTH_LONG)
                    .show()
            }
        }

        viewModel.state.observe(viewLifecycleOwner, ::updateState)
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
