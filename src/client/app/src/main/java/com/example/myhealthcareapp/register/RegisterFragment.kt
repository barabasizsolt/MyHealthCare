package com.example.myhealthcareapp.register

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
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
import com.example.myhealthcareapp.makeAppointment.MakeAppointmentFragment
import com.google.android.material.textfield.TextInputLayout

class RegisterFragment : Fragment() {
    private lateinit var firstName: TextView
    private lateinit var lastName: TextView
    private lateinit var email: TextView
    private lateinit var password: TextView
    private lateinit var firstNameLayout: TextInputLayout
    private lateinit var lastNameLayout: TextInputLayout
    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var registerButton: Button
    private lateinit var loginTextView: TextView
    private lateinit var progressBar : ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        firstName = view.findViewById(R.id.first_name)
        lastName = view.findViewById(R.id.last_name)
        email = view.findViewById(R.id.email)
        password = view.findViewById(R.id.password)
        firstNameLayout = view.findViewById(R.id.first_name_layout)
        lastNameLayout = view.findViewById(R.id.last_name_layout)
        emailLayout = view.findViewById(R.id.email_layout)
        passwordLayout = view.findViewById(R.id.password_layout)
        registerButton = view.findViewById(R.id.register_button)
        loginTextView = view.findViewById(R.id.log_in)
        progressBar = view.findViewById(R.id.progress_bar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerButton.setOnClickListener {
            if(validateInput()){
                progressBar.visibility = View.VISIBLE

                val user = hashMapOf(
                    "firstName" to firstName.text.toString(),
                    "lastName" to lastName.text.toString()
                )

                (activity as MainActivity).mAuth
                    .createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(
                        requireActivity()
                    ) { task ->
                        if (task.isSuccessful) {
                            (activity as MainActivity).firestore.collection("users").document(email.text.toString()).set(user)
                                .addOnSuccessListener {
                                    Toast.makeText(requireActivity(), "Successfully Registered", Toast.LENGTH_LONG).show()
                                    (activity as MainActivity).replaceFragment(MakeAppointmentFragment(), R.id.fragment_container)
                                }
                                .addOnFailureListener { e ->
                                    Log.w(TAG, "Some error occurred during registration", e)
                                }
                        } else {
                            Log.d(TAG, task.exception?.message.toString())
                            Toast.makeText(requireActivity(), "Registration Failed", Toast.LENGTH_LONG).show()
                            Log.d(TAG, email.text.toString())
                            Log.d(TAG, password.text.toString())
                        }

                        progressBar.visibility = View.INVISIBLE
                    }
            }
        }
    }

    private fun validateInput(): Boolean {
        lastNameLayout.error = null
        firstNameLayout.error = null
        emailLayout.error = null
        passwordLayout.error = null

        when{
            firstName.text.toString().isEmpty() -> {
                firstNameLayout.error = getString(R.string.error)
                return false
            }
            lastName.text.toString().isEmpty() -> {
                lastNameLayout.error = getString(R.string.error)
                return false
            }
            email.text.toString().isEmpty() -> {
                emailLayout.error = getString(R.string.error)
                return false
            }
            password.text.toString().isEmpty() -> {
                passwordLayout.error = getString(R.string.error)
                return false
            }
        }
        return true
    }
}