package com.example.myhealthcareapp.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.api.MyHealthCareViewModel
import com.example.myhealthcareapp.cache.Cache
import com.example.myhealthcareapp.fragments.BaseFragment
import com.example.myhealthcareapp.fragments.forgotPassword.ForgotPasswordFragment
import com.example.myhealthcareapp.fragments.makeAppointment.HospitalListFragment
import com.example.myhealthcareapp.fragments.medic.MedicFragment
import com.example.myhealthcareapp.fragments.register.RegisterFragment
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment : BaseFragment() {
    private lateinit var emailTextView : TextView
    private lateinit var passwordTextView : TextView
    private lateinit var emailTextLayout : TextInputLayout
    private lateinit var passwordTextLayout : TextInputLayout
    private lateinit var logInButton : Button
    private lateinit var signUpButton : Button
    private lateinit var clickHereTextView : TextView
    private lateinit var progressBar : ProgressBar
    private lateinit var medicCheckBox: CheckBox
    private val viewModel by sharedViewModel<MyHealthCareViewModel>()

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
        medicCheckBox = view.findViewById(R.id.medic_checkbox)

        viewModel.medicLogin.observe(viewLifecycleOwner, { response ->
            if(response.isSuccessful){
                response.body()?.data?.let { Cache.setMedic(it) }
                medicCheckBox.isChecked = false
                (mActivity as MainActivity).replaceFragment(MedicFragment(), R.id.fragment_container)
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logInButton.setOnClickListener {
            if(validateInput()){
                progressBar.visibility = View.VISIBLE

                if(!medicCheckBox.isChecked) {
                    //Client Login
                    (mActivity as MainActivity).mAuth
                        .signInWithEmailAndPassword(emailTextView.text.toString(), passwordTextView.text.toString())
                        .addOnCompleteListener(
                            requireActivity()
                        ) { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(requireActivity(), "Successfully logged in", Toast.LENGTH_LONG).show()
                                (mActivity as MainActivity).topAppBar.visibility = View.VISIBLE
                                (mActivity as MainActivity).replaceFragment(HospitalListFragment(), R.id.fragment_container)
                            } else {
                                Toast.makeText(requireActivity(), "Incorrect email or password", Toast.LENGTH_LONG).show()
                            }

                            progressBar.visibility = View.INVISIBLE
                        }
                }
                else {
                    //Medic Login
                    viewModel.medicLogin(emailTextView.text.toString(), passwordTextView.text.toString())
                }
            }
        }
        signUpButton.setOnClickListener {
            (mActivity as MainActivity).replaceFragment(RegisterFragment(),R.id.fragment_container,true)
        }
        clickHereTextView.setOnClickListener {
            (mActivity as MainActivity).replaceFragment(ForgotPasswordFragment(),R.id.fragment_container,true)
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