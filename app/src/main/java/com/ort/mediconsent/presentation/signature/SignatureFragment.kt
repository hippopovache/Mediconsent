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
import com.ort.mediconsent.domain.model.Reponse

class SignatureFragment : Fragment() {

    private lateinit var reponses: List<Reponse>
    private lateinit var signature: SignaturePad
    private lateinit var backButton: Button
    private lateinit var validateButton: Button
    private lateinit var resetButton: Button

    private val signatureViewModel: SignatureViewModel by viewModels()

    companion object {
        private const val KEY_ID = "key_id"
        fun newInstance(id: Int, reponses: List<Reponse>): SignatureFragment {

            val bundle = Bundle()
            bundle.putInt(KEY_ID, id)

            val fragment = SignatureFragment()
            fragment.arguments = bundle
            fragment.reponses = reponses

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
            //TODO send signature.signatureBitmap
            signatureViewModel.sendReponses(reponses)
            signatureViewModel.sendSignature(signature.signatureBitmap)

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

            }
        }
    }
}
