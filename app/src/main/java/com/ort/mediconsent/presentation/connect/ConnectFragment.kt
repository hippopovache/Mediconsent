package com.ort.mediconsent.presentation.connect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ort.mediconsent.GlobalVariables
import com.ort.mediconsent.R
import com.ort.mediconsent.presentation.MainActivity

class ConnectFragment : Fragment() {

    private lateinit var connectUsername: EditText
    private lateinit var connectPassword: EditText
    private lateinit var connectButton: Button

    private val viewModel: ConnectViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.connect, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        connectUsername = view.findViewById(R.id.connect_username)
        connectPassword = view.findViewById(R.id.connect_password)
        connectButton = view.findViewById(R.id.connect_button_connect)

        connectButton.setOnClickListener {

            if (connectUsername.text.toString() != "" && connectPassword.text.toString() != "") {
                viewModel.getUserConnect(
                    connectUsername.text.toString(), connectPassword.text.toString()
                ).toString()
                /*viewModel.getUserConnect(
                    connectUsername.text.toString(), connectPassword.text.toString()
                )*/
            } else {
                Toast.makeText(requireContext(), getText(R.string.fillInfo), Toast.LENGTH_SHORT)
                    .show()
            }
        }

        viewModel.state.observe(viewLifecycleOwner, ::updateState)

        /*arguments?.getString(KEY_ID)?.let {
            viewModel.getMovieDetail(it)
        }*/
    }


    private fun updateState(state: ConnectState) {
        when (state) {
            is ConnectState.ErrorState -> {
                Toast.makeText(requireContext(), getText(R.string.wrongConnect), Toast.LENGTH_SHORT)
                    .show()
            }
            is ConnectState.LoadingState -> {
                Toast.makeText(requireContext(), getText(R.string.connecting), Toast.LENGTH_SHORT)
                    .show()
            }
            is ConnectState.SuccessState -> {
                GlobalVariables.isConnected = true
                val activity: MainActivity? = activity as? MainActivity
                activity?.displaySearchLayout()
            }
        }
    }

}
