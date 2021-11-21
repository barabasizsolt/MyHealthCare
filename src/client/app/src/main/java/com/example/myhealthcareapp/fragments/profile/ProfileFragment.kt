package com.example.myhealthcareapp.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.cache.Cache
import com.example.myhealthcareapp.constants.Constant.getDate
import com.example.myhealthcareapp.fragments.BaseFragment
import com.example.myhealthcareapp.fragments.forgotPassword.ForgotPasswordFragment
import com.example.myhealthcareapp.fragments.login.LoginFragment
import kotlinx.android.synthetic.main.activity_main.*

class ProfileFragment : BaseFragment() {
    private lateinit var clientFirstName: TextView
    private lateinit var clientLastName: TextView
    private lateinit var clientPersonalCode: TextView
    private lateinit var clientEmail: TextView
    private lateinit var clientRegistrationDate: TextView
    private lateinit var resetPassword: TextView
    private lateinit var logoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        clientFirstName = view.findViewById(R.id.first_name)
        clientLastName = view.findViewById(R.id.last_name)
        clientPersonalCode = view.findViewById(R.id.personal_code)
        clientEmail = view.findViewById(R.id.email)
        clientRegistrationDate = view.findViewById(R.id.registration_date)
        resetPassword = view.findViewById(R.id.click_here_text_view)
        logoutButton = view.findViewById(R.id.logout_button)

        (mActivity as MainActivity).topAppBar.menu.findItem(R.id.search).isVisible = false
        (mActivity as MainActivity).topAppBar.menu.findItem(R.id.profile).isVisible = true
        (mActivity as MainActivity).topAppBar.title = getString(R.string.profile_title)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val client = Cache.getClient()
        clientFirstName.text = client.clientFirstName
        clientLastName.text = client.clientLastName
        clientPersonalCode.text = client.clientPersonalCode
        clientEmail.text = client.clientEmail
        clientRegistrationDate.text = getDate((activity as MainActivity).mAuth.currentUser?.metadata?.creationTimestamp!!)
        logoutButton.setOnClickListener{
            (mActivity as MainActivity).mAuth.signOut()
            (mActivity as MainActivity).topAppBar.visibility = View.GONE
            (mActivity as MainActivity).replaceFragment(LoginFragment(), R.id.fragment_container)
            (mActivity as MainActivity).bottomNavigation.visibility = View.GONE
        }
        resetPassword.setOnClickListener {
            (mActivity as MainActivity).replaceFragment(ForgotPasswordFragment(), R.id.fragment_container, true)
        }
    }
}