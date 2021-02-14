package com.ort.mediconsent.presentation.avis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ort.mediconsent.R
import com.ort.mediconsent.domain.model.Avis
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.presentation.MainActivity

class AvisFragment : Fragment() {

    private lateinit var backButton: Button
    private lateinit var validateButton: Button
    private lateinit var ratingBar: RatingBar
    private lateinit var comment: EditText
    private lateinit var examen: Examen

    private val avisViewModel: AvisViewModel by viewModels()

    companion object {
        fun newInstance(examen: Examen): AvisFragment {
            val bundle = Bundle()
            val fragment = AvisFragment()
            fragment.examen = examen
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        avisViewModel.state.observe(viewLifecycleOwner, ::updateState)

        validateButton = view.findViewById(R.id.review_validate)
        ratingBar = view.findViewById(R.id.review_rating)
        comment = view.findViewById(R.id.review_comment)
        backButton = view.findViewById(R.id.review_back)

        validateButton.setOnClickListener {
            if (ratingBar.rating.toDouble() == 0.0) {
                Toast.makeText(
                    requireContext(),
                    "S'il vous plait, séléctionnez une note.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                println(ratingBar.rating.toInt())
                avisViewModel.sendAvis(
                    examen,
                    Avis(0, ratingBar.rating.toInt(), comment.text.toString())
                )
            }
        }
        backButton.setOnClickListener {
            parentFragmentManager.popBackStackImmediate()
        }

    }

    private fun updateState(state: AvisState) {
        when (state) {
            is AvisState.ErrorState -> {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            }
            is AvisState.LoadingState -> {
            }
            is AvisState.SuccessState -> {
                Toast.makeText(
                    requireContext(),
                    "Merci pour votre avis",
                    Toast.LENGTH_LONG
                ).show()
                val activity: MainActivity = activity as MainActivity
                activity.displaySearchLayout()
            }
        }
    }
}
