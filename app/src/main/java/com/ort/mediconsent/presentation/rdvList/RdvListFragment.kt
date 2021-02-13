package com.ort.mediconsent.presentation.rdvList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ort.mediconsent.R
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.presentation.MainActivity

class RdvListFragment : Fragment() {

    private lateinit var text1: TextView
    private lateinit var consentButton: Button
    private lateinit var backButton: Button
    private lateinit var examen: Examen

    private val viewModel: RdvListViewModel by viewModels()

    companion object {
        private const val KEY_ID = "key_id"

        fun newInstance(id: Int): RdvListFragment {
            val bundle = Bundle()
            bundle.putInt(KEY_ID, id)

            val fragment = RdvListFragment()
            fragment.arguments = bundle

            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rdv_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text1 = view.findViewById(R.id.rdv_list_textview_info_1)
        consentButton = view.findViewById(R.id.rdv_list_button_consent)
        backButton = view.findViewById(R.id.rdv_list_button_back)

        consentButton.setOnClickListener {
            if (this::examen.isInitialized) {
                val activity: MainActivity = activity as MainActivity
                println(examen.consentement)
                if (!examen.consentement) {
                    activity.displayQuestionsLayout(
                        examen.typeExamen.formulaire.id_formulaire,
                        examen
                    )
                } else {
                    activity.displayAvisLayout(examen)
                }
            } else {
                println("this::examen.isInitialized = false")
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            }

        }
        backButton.setOnClickListener {
            parentFragmentManager.popBackStackImmediate()
        }
        viewModel.state.observe(viewLifecycleOwner, ::updateState)

        arguments?.getInt(KEY_ID)?.let {
            viewModel.getExamenDetails(it)
        }
    }

    private fun updateState(state: RdvListState) {
        when (state) {
            is RdvListState.ErrorState -> {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            }
            is RdvListState.LoadingState -> {
            }
            is RdvListState.SuccessState -> {
                examen = state.examen
                if (state.examen.consentement) {
                    text1.text = "Vous avez participé à un examen, souhaitez-vous l'évaluer?"
                } else {
                    text1.append(
                        android.text.format.DateFormat.format(
                            "hh:mm",
                            state.examen.date_examen
                        )
                    )
                    text1.append(" ")
                    println(state.examen.toString())
                    //TODO
                    println(R.string.typeRDV.toString())
                    //text1.append(R.string.typeRDV.toString())
                    if (state.examen.typeExamen.libelle_type_examen != null) {
                        text1.append(state.examen.typeExamen.libelle_type_examen)
                    }
                }
            }
        }
    }


}
