package com.ort.mediconsent.presentation.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ort.mediconsent.R
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.model.Question
import com.ort.mediconsent.domain.model.Reponse
import com.ort.mediconsent.presentation.MainActivity

class QuestionFragment : Fragment() {

    private lateinit var textview: TextView
    private lateinit var edittext: EditText
    private lateinit var btnNext: Button
    private lateinit var backButton: Button
    private lateinit var question: Question
    private lateinit var yes: RadioButton
    private lateinit var no: RadioButton
    private lateinit var radioGroup: RadioGroup
    private lateinit var questions: List<Question>
    private lateinit var examen: Examen
    private lateinit var prenom: String
    private lateinit var nom: String

    private val questionViewModel: QuestionViewModel by viewModels()

    companion object {
        private const val KEY_ID = "key_id"
        fun newInstance(id: Int, examenGet: Examen, prenom: String, nom: String): QuestionFragment {

            val bundle = Bundle()
            bundle.putInt(KEY_ID, id)

            val fragment = QuestionFragment()
            fragment.arguments = bundle
            fragment.examen = examenGet
            fragment.nom = nom
            fragment.prenom = prenom

            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textview = view.findViewById(R.id.question_text_view)
        edittext = view.findViewById(R.id.question_edittext)
        backButton = view.findViewById(R.id.question_btn_back)
        btnNext = view.findViewById(R.id.question_btn_next)
        radioGroup = view.findViewById(R.id.question_radio_group)
        var index = 0
        yes = view.findViewById(R.id.question_yes)
        no = view.findViewById(R.id.question_no)
        val reponses = mutableListOf<Reponse>()

        questionViewModel.state.observe(viewLifecycleOwner, ::updateState)

        arguments?.getInt(KEY_ID)?.let {
            questionViewModel.getExamenQuestions(it)
        }

        btnNext.setOnClickListener {
            if (question.id_question != 0) {
                if (edittext.text.toString() == "" && radioGroup.checkedRadioButtonId == -1) {
                    Toast.makeText(
                        requireContext(),
                        getText(R.string.fillInfo),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (edittext.text.toString() != "") {
                        reponses.add(
                            Reponse(
                                examen,
                                question,
                                edittext.text.toString()
                            )
                        )
                    } else {
                        if (radioGroup.checkedRadioButtonId == R.id.question_yes) {
                            reponses.add(Reponse(examen, question, getString(R.string.yes)))
                        } else {
                            reponses.add(Reponse(examen, question, getString(R.string.no)))
                        }
                    }
                    index = questions.indexOf(question)
                    index += 1
                    if (index >= questions.size) {
                        Toast.makeText(
                            requireContext(),
                            getText(R.string.thanksForAnswers),
                            Toast.LENGTH_SHORT
                        ).show()
                        val activity: MainActivity = activity as MainActivity
                        activity.displaySignatureLayout(examen, reponses, questions, prenom, nom)
                    } else {
                        question = questions[index]
                        edittext.setText("")
                        radioGroup.clearCheck()
                        if (question.isCheckbox) {
                            edittext.isVisible = false
                            radioGroup.isVisible = true
                        } else {
                            edittext.isVisible = true
                            radioGroup.isVisible = false
                        }
                        textview.text = question.libelle_question
                    }
                }
            }
        }
        backButton.setOnClickListener {
            if (reponses.isEmpty()) {
                parentFragmentManager.popBackStackImmediate()
            } else {
                reponses.removeLast()
                index -= 1
                question = questions[index]
                edittext.setText("")
                radioGroup.clearCheck()
                textview.text = question.libelle_question
            }
        }

    }

    private fun updateState(state: QuestionState) {
        when (state) {
            is QuestionState.ErrorState -> {
                Toast.makeText(requireContext(), "Error question", Toast.LENGTH_SHORT).show()
            }
            is QuestionState.LoadingState -> {
            }
            is QuestionState.SuccessState -> {
                questions = state.question
                question = state.question.first()
                textview.text = question.libelle_question
                if (question.isCheckbox) {
                    edittext.isVisible = false
                    radioGroup.isVisible = true
                } else {
                    edittext.isVisible = true
                    radioGroup.isVisible = false
                }
                edittext.setText("")
                radioGroup.clearCheck()
            }
        }
    }


}
