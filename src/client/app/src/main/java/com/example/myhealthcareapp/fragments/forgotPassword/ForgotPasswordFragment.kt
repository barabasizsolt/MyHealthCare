package com.example.myhealthcareapp.fragments.forgotPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.fragments.BaseFragment
import com.example.myhealthcareapp.fragments.login.LoginFragment
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*

class ForgotPasswordFragment : BaseFragment() {
    private lateinit var email: TextView
    private lateinit var emailLayout: TextInputLayout
    private lateinit var resetButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)

        email = view.findViewById(R.id.email_text_view)
        emailLayout = view.findViewById(R.id.email_text_layout)
        resetButton = view.findViewById(R.id.email_me)

        (mActivity as MainActivity).topAppBar.title = getString(R.string.reset_password)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resetButton.setOnClickListener {
            if(validateEmail()){
                (mActivity as MainActivity).mAuth.sendPasswordResetEmail(email.text.toString())
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireContext(), "Reset link sent to your email", Toast.LENGTH_LONG).show()
                            (activity as MainActivity).replaceFragment(LoginFragment(), R.id.fragment_container)
                        } else {
                            Toast.makeText(requireContext(), "Unable to send reset mail", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }

    private fun validateEmail(): Boolean {
        emailLayout.error = null

        when{
            email.text.toString().isEmpty() -> {
                emailLayout.error = getString(R.string.error)
                return false
            }
        }
        return true
    }
}