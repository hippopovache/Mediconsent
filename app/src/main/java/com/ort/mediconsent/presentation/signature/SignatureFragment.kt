package com.ort.mediconsent.presentation.signature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.gcacace.signaturepad.views.SignaturePad
import com.ort.mediconsent.R
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.model.Reponse
import com.ort.mediconsent.presentation.MainActivity

class SignatureFragment : Fragment() {

    private lateinit var reponses: List<Reponse>
    private lateinit var signature: SignaturePad
    private lateinit var backButton: Button
    private lateinit var validateButton: Button
    private lateinit var resetButton: Button
    private lateinit var examen: Examen

    private val signatureViewModel: SignatureViewModel by viewModels()

    companion object {
        private const val KEY_ID = "key_id"
        fun newInstance(examen: Examen, reponses: List<Reponse>): SignatureFragment {

            val bundle = Bundle()

            val fragment = SignatureFragment()
            fragment.arguments = bundle
            fragment.reponses = reponses
            fragment.examen = examen

            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.signature, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signatureViewModel.state.observe(viewLifecycleOwner, ::updateState)
        signatureViewModel.sendReponses(reponses)

        signature = view.findViewById(R.id.signature_pad)
        backButton = view.findViewById(R.id.signature_btn_back)
        validateButton = view.findViewById(R.id.signature_btn_validate)
        resetButton = view.findViewById(R.id.signature_btn_reset)


        arguments?.getInt(KEY_ID)?.let {
            //signatureViewModel.getExamenQuestions(it)
        }

        validateButton.setOnClickListener {
            signatureViewModel.sendReponses(reponses)
            signatureViewModel.sendSignature(examen, signature.signatureBitmap)

        }
        resetButton.setOnClickListener {
            signature.clear()
        }
        backButton.setOnClickListener {
            parentFragmentManager.popBackStackImmediate()
        }

    }

    private fun updateState(state: SignatureState) {
        when (state) {
            is SignatureState.ErrorState -> {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            }
            is SignatureState.LoadingState -> {
            }
            is SignatureState.SuccessState -> {
                Toast.makeText(
                    requireContext(),
                    "Enregistrement éfféctué, revenez après votre examen afin de le noter",
                    Toast.LENGTH_LONG
                ).show()
                val activity: MainActivity = activity as MainActivity
                activity.displaySearchLayout()
            }
        }
    }

    private fun updateState(state: ReponseState) {
        when (state) {
            is ReponseState.ErrorState -> {
                Toast.makeText(
                    requireContext(),
                    "Erreur dans l'envoi des réponses",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is ReponseState.LoadingState -> {
            }
            is ReponseState.SuccessState -> {

            }
        }
    }
}
