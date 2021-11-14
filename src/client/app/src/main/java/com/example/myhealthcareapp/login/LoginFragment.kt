package com.example.myhealthcareapp.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.forgotPassword.ForgotPasswordFragment
import com.example.myhealthcareapp.makeAppointment.MakeAppointmentFragment
import com.example.myhealthcareapp.register.RegisterFragment
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*

class LoginFragment : Fragment() {
    private lateinit var emailTextView : TextView
    private lateinit var passwordTextView : TextView
    private lateinit var emailTextLayout : TextInputLayout
    private lateinit var passwordTextLayout : TextInputLayout
    private lateinit var logInButton : Button
    private lateinit var signUpButton : Button
    private lateinit var clickHereTextView : TextView
    private lateinit var progressBar : ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_login, container, false)

        emailTextView = view.findViewById(R.id.email_text_view)
        passwordTextView = view.findViewById(R.id.password_text_view)
        emailTextLayout = view.findViewById(R.id.email_text_view_layout)
        passwordTextLayout = view.findViewById(R.id.password_text_view_layout)
        logInButton = view.findViewById(R.id.log_in_button)
        signUpButton = view.findViewById(R.id.sign_up_button)
        clickHereTextView = view.findViewById(R.id.click_here_text_view)
        progressBar = view.findViewById(R.id.progress_bar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logInButton.setOnClickListener {
            if(validateInput()){
                progressBar.visibility = View.VISIBLE

                (activity as MainActivity).mAuth
                    .signInWithEmailAndPassword(emailTextView.text.toString(), passwordTextView.text.toString())
                    .addOnCompleteListener(
                        requireActivity()
                    ) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireActivity(), "Successfully logged in", Toast.LENGTH_LONG).show()
                            (activity as MainActivity).topAppBar.visibility = View.VISIBLE
                            (activity as MainActivity).replaceFragment(MakeAppointmentFragment(), R.id.fragment_container)
                        } else {
                            Toast.makeText(requireActivity(), "Incorrect email or password", Toast.LENGTH_LONG).show()
                        }

                        progressBar.visibility = View.INVISIBLE
                    }
            }
        }
        signUpButton.setOnClickListener {
            (activity as MainActivity).replaceFragment(RegisterFragment(),R.id.fragment_container,true)
        }
        clickHereTextView.setOnClickListener {
            (activity as MainActivity).replaceFragment(ForgotPasswordFragment(),R.id.fragment_container,true)
        }
    }


    private fun validateInput(): Boolean {
        emailTextLayout.error = null
        passwordTextLayout.error = null

        when{
            emailTextView.text.toString().isEmpty() -> {
                emailTextLayout.error = getString(R.string.error)
                return false
            }
            passwordTextView.text.toString().isEmpty() -> {
                passwordTextLayout.error = getString(R.string.error)
                return false
            }
        }
        return true
    }
}