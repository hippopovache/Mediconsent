package com.ort.mediconsent.presentation.signature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.gcacace.signaturepad.views.SignaturePad
import com.ort.mediconsent.R
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.model.Question
import com.ort.mediconsent.domain.model.Reponse
import com.ort.mediconsent.presentation.MainActivity


class SignatureFragment : Fragment() {

    private lateinit var reponses: List<Reponse>
    private lateinit var questions: List<Question>
    private lateinit var signature: SignaturePad
    private lateinit var backButton: Button
    private lateinit var validateButton: Button
    private lateinit var resetButton: Button
    private lateinit var examen: Examen
    private lateinit var prenom: String
    private lateinit var nom: String

    companion object {
        fun newInstance(
            examen: Examen,
            reponses: List<Reponse>,
            questions: List<Question>,
            prenom: String,
            nom: String,
        ): SignatureFragment {

            val bundle = Bundle()

            val fragment = SignatureFragment()
            fragment.arguments = bundle
            fragment.reponses = reponses
            fragment.examen = examen
            fragment.nom = nom
            fragment.prenom = prenom
            fragment.questions = questions

            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.signature, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signature = view.findViewById(R.id.signature_pad)
        backButton = view.findViewById(R.id.signature_btn_back)
        validateButton = view.findViewById(R.id.signature_btn_validate)
        resetButton = view.findViewById(R.id.signature_btn_reset)

        validateButton.setOnClickListener {
            if (!signature.isEmpty) {


                val activity: MainActivity = activity as MainActivity
                activity.displayPdfLayout(
                    examen,
                    questions,
                    reponses,
                    signature.signatureBitmap,
                    prenom,
                    nom
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    getText(R.string.fillInfo),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        resetButton.setOnClickListener {
            signature.clear()
        }
        backButton.setOnClickListener {
            parentFragmentManager.popBackStackImmediate()
        }

    }
}
