package com.example.myhealthcareapp.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.forgotPassword.ForgotPasswordFragment
import com.example.myhealthcareapp.register.RegisterFragment


class LoginFragment : Fragment() {

    private lateinit var signUpButton : Button
    private lateinit var clickHereTextView : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_login, container, false)

        signUpButton = view.findViewById(R.id.sign_up_button)
        clickHereTextView = view.findViewById(R.id.click_here_text_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpButton.setOnClickListener {
            (activity as MainActivity).replaceFragment(RegisterFragment(),R.id.fragment_container,true)
        }
        clickHereTextView.setOnClickListener {
            (activity as MainActivity).replaceFragment(ForgotPasswordFragment(),R.id.fragment_container,true)
        }
    }

}