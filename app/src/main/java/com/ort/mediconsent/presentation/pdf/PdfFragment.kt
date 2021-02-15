package com.ort.mediconsent.presentation.pdf

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.barteksc.pdfviewer.PDFView
import com.ort.mediconsent.R
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.model.Question
import com.ort.mediconsent.domain.model.Reponse
import com.ort.mediconsent.presentation.MainActivity
import com.ort.mediconsent.presentation.signature.ReponseState
import com.ort.mediconsent.presentation.signature.SignatureState
import com.ort.mediconsent.presentation.signature.SignatureViewModel
import java.io.File
import java.io.FileOutputStream


class PdfFragment : Fragment() {

    private lateinit var reponses: List<Reponse>
    private lateinit var questions: List<Question>
    private lateinit var pdfView: PDFView
    private lateinit var signatureBitmap: Bitmap
    private lateinit var backButton: Button
    private lateinit var validateButton: Button
    private lateinit var examen: Examen
    private lateinit var prenom: String
    private lateinit var nom: String

    private val signatureViewModel: SignatureViewModel by viewModels()

    companion object {
        fun newInstance(
            examen: Examen,
            questions: List<Question>,
            reponses: List<Reponse>,
            signature: Bitmap,
            prenom: String,
            nom: String,
        ): PdfFragment {

            val bundle = Bundle()

            val fragment = PdfFragment()
            fragment.arguments = bundle
            fragment.reponses = reponses
            fragment.examen = examen
            fragment.nom = nom
            fragment.prenom = prenom
            fragment.signatureBitmap = signature
            fragment.questions = questions
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.pdf_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signatureViewModel.state.observe(viewLifecycleOwner, ::updateState)
        signatureViewModel.reponseState.observe(viewLifecycleOwner, ::updateState)
        backButton = view.findViewById(R.id.pdf_btn_back)
        validateButton = view.findViewById(R.id.pdf_btn_validate)
        pdfView = view.findViewById(R.id.pdf_view)


        // create a new document
        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(600, 600, 1).create()
        val page: PdfDocument.Page = document.startPage(pageInfo)
        val paint = Paint()
        val x = 5F
        var y = 50F
        page.canvas.drawText("Formulaire de consentement", 230F, y, paint)
        y += paint.descent() - paint.ascent()
        y += paint.descent() - paint.ascent()
        y += paint.descent() - paint.ascent()

        for ((indexQuestion, question) in questions.withIndex()) {
            page.canvas.drawText(
                question.libelle_question + " : " + reponses[indexQuestion].reponse,
                x,
                y,
                paint
            )
            y += paint.descent() - paint.ascent()
        }
        page.canvas.drawText("Madame, Mademoiselle, Monsieur $nom $prenom", x, y, paint)
        y += paint.descent() - paint.ascent()
        /*val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val formatedDate = formatter.format(parser.parse(examen.date_examen.toString()))*/
        page.canvas.drawText(
            "a personnellement rempli cette fiche le ${examen.date_examen}",
            x,
            y,
            paint
        )
        y += paint.descent() - paint.ascent()
        y += paint.descent() - paint.ascent()
        page.canvas.drawText("et donné son accord pour que l'examen soit réalisé ", x, y, paint)
        y += paint.descent() - paint.ascent()
        y += paint.descent() - paint.ascent()
        page.canvas.drawText("signature ", x, y, paint)
        y += paint.descent() - paint.ascent()
        y += paint.descent() - paint.ascent()
        val signatureRescaled = Bitmap.createScaledBitmap(
            signatureBitmap,
            (signatureBitmap.width * 0.1).toInt(),
            (signatureBitmap.height * 0.1).toInt(),
            true
        )
        page.canvas.drawBitmap(signatureRescaled, x, y, paint)
        document.finishPage(page)

        //Création d'un fichier temporaire dans le cache de l'application
        val outputDir: File = requireContext().cacheDir
        val outputFile: File = File.createTempFile("pdfFile", ".pdf", outputDir)
        document.writeTo(FileOutputStream(outputFile))
        document.close()


        //Chargement du fichier PDF
        pdfView.fromFile(outputFile).load()


        validateButton.setOnClickListener {
            signatureViewModel.sendReponses(reponses)
            signatureViewModel.sendSignaturePdf(examen, outputFile, signatureBitmap)

            backButton.setOnClickListener {
                println("yoyoLove")
                parentFragmentManager.popBackStackImmediate()
            }
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
                    getText(R.string.saveDone),
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
                    "Error",
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
