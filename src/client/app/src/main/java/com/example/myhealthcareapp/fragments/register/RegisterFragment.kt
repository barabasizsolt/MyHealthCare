package com.example.myhealthcareapp.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.api.MyHealthCareViewModel
import com.example.myhealthcareapp.constants.Constant
import com.example.myhealthcareapp.fragments.BaseFragment
import com.example.myhealthcareapp.fragments.makeAppointment.HospitalListFragment
import com.example.myhealthcareapp.fragments.login.LoginFragment
import com.example.myhealthcareapp.models.user.Client
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : BaseFragment() {
    private lateinit var firstName: TextView
    private lateinit var lastName: TextView
    private lateinit var email: TextView
    private lateinit var personalCode: TextView
    private lateinit var password: TextView
    private lateinit var firstNameLayout: TextInputLayout
    private lateinit var lastNameLayout: TextInputLayout
    private lateinit var emailLayout: TextInputLayout
    private lateinit var personalCodeLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var registerButton: Button
    private lateinit var loginTextView: TextView
    private lateinit var progressBar : ProgressBar

    private val viewModel by viewModel<MyHealthCareViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        firstName = view.findViewById(R.id.first_name)
        lastName = view.findViewById(R.id.last_name)
        email = view.findViewById(R.id.email)
        personalCode = view.findViewById(R.id.personal_code)
        password = view.findViewById(R.id.password)
        firstNameLayout = view.findViewById(R.id.first_name_layout)
        lastNameLayout = view.findViewById(R.id.last_name_layout)
        emailLayout = view.findViewById(R.id.email_layout)
        personalCodeLayout = view.findViewById(R.id.personal_code_layout)
        passwordLayout = view.findViewById(R.id.password_layout)
        registerButton = view.findViewById(R.id.register_button)
        loginTextView = view.findViewById(R.id.log_in)
        progressBar = view.findViewById(R.id.progress_bar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.registration.observe(viewLifecycleOwner, { response ->
            if(response.isSuccessful) {
                Toast.makeText(requireActivity(), "Successfully Registered", Toast.LENGTH_LONG).show()
                (mActivity as MainActivity).topAppBar.visibility = View.VISIBLE
                (mActivity as MainActivity).replaceFragment(HospitalListFragment(), R.id.fragment_container)
            }
        })

        registerButton.setOnClickListener {
            if(validateInput()){
                progressBar.visibility = View.VISIBLE

                (mActivity as MainActivity).mAuth
                    .createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(
                        requireActivity()
                    ) { task ->
                        if (task.isSuccessful) {
                            val client = Client(
                                id = Constant.CLIENT_ID,
                                name = firstName.text.toString() + " " + lastName.text.toString(),
                                personalCode = personalCode.text.toString(),
                                email = email.text.toString(),
                                password = password.text.toString()
                            )
                            viewModel.registerClient(client)
                        } else {
                            Toast.makeText(requireActivity(), "Registration Failed", Toast.LENGTH_LONG).show()
                        }

                        progressBar.visibility = View.INVISIBLE
                    }
            }
        }

        loginTextView.setOnClickListener {
            (mActivity as MainActivity).replaceFragment(LoginFragment(), R.id.fragment_container)
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
            personalCode.text.toString().isEmpty() -> {
                personalCodeLayout.error = getString(R.string.error)
                return false
            }
            !personalCode.text.matches(Regex(Constant.CNP_REGEX)) -> {
                personalCodeLayout.error = getString(R.string.cnp_error)
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