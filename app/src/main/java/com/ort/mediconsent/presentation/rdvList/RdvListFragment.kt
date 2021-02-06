package com.ort.mediconsent.presentation.rdvList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ort.mediconsent.R
import com.ort.mediconsent.presentation.MainActivity

class RdvListFragment : Fragment() {

    private lateinit var text1: TextView
    private lateinit var text2: TextView
    private lateinit var consentButton: Button
    private lateinit var backButton: Button


    private val viewModel: RdvListViewModel by viewModels()
    private val activity: MainActivity? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text1 = view.findViewById(R.id.rdv_list_textview_info_1)
        text2 = view.findViewById(R.id.rdv_list_textview_info_2)
        consentButton = view.findViewById(R.id.rdv_list_button_consent)
        backButton = view.findViewById(R.id.rdv_list_button_back)

        consentButton.setOnClickListener {
        }
        backButton.setOnClickListener {
        }

        viewModel.state.observe(viewLifecycleOwner, ::updateState)
    }

    private fun updateState(state: RdvListState) {
        when (state) {
            is RdvListState.ErrorState -> {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            }
            is RdvListState.LoadingState -> {
            }
            is RdvListState.SuccessState -> {
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                val activity: MainActivity? = activity
            }
        }
    }

}
